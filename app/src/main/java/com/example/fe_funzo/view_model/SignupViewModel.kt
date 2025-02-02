package com.example.fe_funzo.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.util.FirebaseAuthUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.view.Signup

class SignupViewModel(
    var email: String  ="",
    var password : String  ="",
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

    fun signUp(email: String, password: String, context: Signup) {
        if(FirebaseAuthUtil.signUp(email=email, password=password, context=context)) {
            NavigationUtil.navigateToLandingPage()
        } else {
            Log.i(TAG, "signUp failed")
        }
    }
}
