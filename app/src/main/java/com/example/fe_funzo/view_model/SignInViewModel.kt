package com.example.fe_funzo.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.fe_funzo.infa.util.FirebaseAuthUtil
import com.example.fe_funzo.view.SignIn

class SignInViewModel (var showErrorMessage: MutableState<Boolean> = mutableStateOf(false),
                       var errorMessage: MutableState<String> = mutableStateOf(""),
                       var email: MutableState<String> = mutableStateOf(""),
                       var password: MutableState<String> = mutableStateOf("")) {

    fun signIn(email: String, password: String, signInContext: SignIn) {
        FirebaseAuthUtil.signIn(email, password, context = signInContext) {}
    }
}
