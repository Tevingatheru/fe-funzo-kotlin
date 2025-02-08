package com.example.fe_funzo.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.client.FirebaseAuthClient
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.view.Signup
import kotlinx.coroutines.runBlocking

class SignupViewModel(
    var email: MutableState<String>  = mutableStateOf(""),
    var password : MutableState<String>  = mutableStateOf(""),
    var showErrorMessage: MutableState<Boolean> = mutableStateOf(false),
    var name:String  ="",
    var message: MutableState<String> = mutableStateOf(""),
):ViewModel() {

    companion object {
        private const val TAG = "SignupViewModel"
    }

    fun setErrorMessage() {
        message = mutableStateOf("Email and password are required")
        showErrorMessage = mutableStateOf(true)
        Log.i(TAG, "Message: ${message.value}, show: ${showErrorMessage}")
    }

    fun signUp(email: String, password: String, signup: Signup) {
        Log.i(TAG, "signUp")
        runBlocking {
            FirebaseAuthClient.signUp(email=email, password=password, signup=signup) { isSuccessful ->
                if (isSuccessful) {
                    NavigationUtil.navigateToLandingPage(context = signup)
                } else {
                    Log.i(TAG, "Sign up failed")
                }
            }
        }
    }
}
