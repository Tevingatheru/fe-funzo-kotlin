package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.room.response.UserResponse
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.UserClient
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.data.model.UserType
import kotlinx.coroutines.runBlocking

class SignInViewModel (var showErrorMessage: MutableState<Boolean> = mutableStateOf(false),
                       var errorMessage: MutableState<String> = mutableStateOf(""),
                       var email: MutableState<String> = mutableStateOf(""),
                       var password: MutableState<String> = mutableStateOf("")): ViewModel() {

    companion object {
        const val TAG: String = "SignInViewModel"
    }

    fun signIn(email: String, password: String, signInContext: SignIn) {

        val userService: UserClientServiceImpl = UserClientServiceImpl()

        try {
            Log.i(TAG, "sign in callback success")

            runBlocking {
                val userResponse: UserResponse = userService.getUserByEmail(email)
                val userType: UserType = userResponse.getUserType()

                UserRepoServiceImpl(context = signInContext)
                    .save( email = email, response = userResponse)
                NavigationUtil.navigateToLandingPage(
                    context = signInContext,
                    userType = userType
                )
                FirebaseAuthClient.signIn(email, password, context = signInContext)
            }
        } catch (e: Exception) {
            Log.e(TAG, "sign in callback failure. \nError: $e")
            EventAlertUtil.signInHasFailed(context = signInContext)
            throw e
        }
    }
}
