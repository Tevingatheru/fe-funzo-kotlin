package com.example.fe_funzo.logic.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.UserRepoService
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl

class FirebaseViewModel: ViewModel() {
    companion object {
        private const val TAG = "FirebaseViewModel"
    }

    fun validateCurrentUser(context: Context) {
        if (!FirebaseAuthClient.isUserLoggedIn()) {
            NavigationUtil.navigateToSignUpActivity(context)
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
