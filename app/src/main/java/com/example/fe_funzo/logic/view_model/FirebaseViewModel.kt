package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.model.UserType
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import kotlinx.coroutines.runBlocking

class FirebaseViewModel: ViewModel() {
    companion object {
        private const val TAG = "FirebaseViewModel"
    }

    fun isUserLoggedOut(context: Context, ) {
        if (!FirebaseAuthClient.isUserLoggedIn()) {
            NavigationUtil.navigateToSignUpActivity(context)
        }
    }

    fun isUserLoggedIn(context: Context) {
        if (FirebaseAuthClient.isUserLoggedIn()) {
            val userRepository: UserRepoServiceImpl = UserRepoServiceImpl(context = context)

            NavigationUtil.navigateToLandingPage(context = context, userType = getUserType(userRepository = userRepository))
        }
    }

    private fun getUserType(userRepository: UserRepoServiceImpl): UserType {
        return runBlocking {
            val user: User = userRepository.getFirstUser()
            val userType = UserType.find(user.userType)
            userType
        }
    }

    fun logout(context: Context) {
        val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context= context)
        val user:User? = userRepoServiceImpl.getFirstUser()

        if (FirebaseAuthClient.isUserLoggedIn()) {
            FirebaseAuthClient.logout()
        }

        if (user != null) {
            userRepoServiceImpl.delete(user = user)
        }

        NavigationUtil.navigateToSignUpActivity(context = context)
    }
}
