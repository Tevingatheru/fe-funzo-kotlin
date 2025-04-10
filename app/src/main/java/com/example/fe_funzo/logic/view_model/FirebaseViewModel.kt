package com.example.fe_funzo.logic.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.data.room.entity.User
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import com.example.fe_funzo.logic.service.repo.impl.ExamRepositoryServiceImpl
import com.example.fe_funzo.presentation.view.SignIn

class FirebaseViewModel: ViewModel() {
    companion object {
        private const val TAG = "FirebaseViewModel"
    }

    fun isUserLoggedOut(context: Context, ): Boolean {
        Log.i(TAG, "isUserLoggedOut. Context: ${context.packageName}")
        val userRepoServiceImpl = UserRepoServiceImpl(context)
        val isLocallyLoggedIn = userRepoServiceImpl.isLoggedIn()

        val isUserLoggedInViaFirebase = FirebaseAuthClient.isUserLoggedIn()

        when {
            isLocallyLoggedIn && isUserLoggedInViaFirebase -> {
                return false
            }
            isLocallyLoggedIn && !isUserLoggedInViaFirebase -> {
                val user = userRepoServiceImpl.getFirstUser()
                userRepoServiceImpl.deleteAll()
                NavigationUtil.navigateToSignUpActivity(context)
                return true
            }
            !isLocallyLoggedIn && !isUserLoggedInViaFirebase -> {
                NavigationUtil.navigateToSignUpActivity(context)
                return true
            }
            else -> {
                FirebaseAuthClient.logout()
                NavigationUtil.navigateToSignUpActivity(context)
                return true
            }
        }
    }

    fun isUserLoggedIn(context: Context) {
        Log.i(TAG, "isUserLoggedIn. Context: ${context.packageName}")
        if (FirebaseAuthClient.isUserLoggedIn()) {
            NavigationUtil.navigateToLandingPage(context = context)
        }
    }

    fun logout(context: Context) {
        Log.i(TAG, "logout. Context: ${context.packageName}")

        val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context= context)
        val user: User? = userRepoServiceImpl.getFirstUser()
        val examRepoServiceImpl: ExamRepositoryServiceImpl = ExamRepositoryServiceImpl(
            context = context,
        )

        if (FirebaseAuthClient.isUserLoggedIn()) {
            FirebaseAuthClient.logout()
        }

        if (user != null) {
            userRepoServiceImpl.deleteAll()
            examRepoServiceImpl.deleteExistingExam()
        }

        NavigationUtil.navigateToSignUpActivity(context = context)
    }
}
