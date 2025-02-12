package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.fe_funzo.infa.client.FirebaseAuthClient
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.UserRepoServiceImpl
import com.example.fe_funzo.presentation.view.SignIn
import com.funzo.funzoProxy.domain.user.UserType

class SignInViewModel (var showErrorMessage: MutableState<Boolean> = mutableStateOf(false),
                       var errorMessage: MutableState<String> = mutableStateOf(""),
                       var email: MutableState<String> = mutableStateOf(""),
                       var password: MutableState<String> = mutableStateOf("")) {

    companion object {
        const val TAG: String = "SignInViewModel"
    }

    fun signIn(email: String, password: String, signInContext: SignIn) {
        FirebaseAuthClient.signIn(email, password, context = signInContext) {
            Log.i(TAG, "sign in callback")
            if(it) {
                Log.i(TAG, "sign in callback success")
                val userType = getUserTypeByEmail(email)
                val userRepoServiceImpl = UserRepoServiceImpl()
                userRepoServiceImpl.save(userType, signInContext, email)
                NavigationUtil.navigateToLandingPage(context = signInContext, userType = userType)
            } else {
                Log.i(TAG, "sign in callback failure")
            }
        }
    }

    private fun getUserTypeByEmail(email: String): UserType {
        val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl()
        val user: User = userRepoServiceImpl.getUserByEmail(email)
        TODO("Not yet implemented")
        return UserType.find(user.userType)
    }
}
