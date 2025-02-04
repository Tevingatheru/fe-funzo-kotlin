package com.example.fe_funzo.view_model

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.dto.SignupRequest
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil

class AuthFormSignUpStrategy : AuthFormStrategy<SignupRequest> {
    companion object {
        const val TAG = "AuthenticationFormView"
    }

    @Composable
    override fun Display(request: SignupRequest) {
        val email1 = request.signupVM.email.value
        val password1 = request.signupVM.password.value

        Text(text = "Hello ${request.signupVM.name}!", modifier = Modifier.padding(bottom = 16.dp))
        if (request.signupVM.showErrorMessage.value) {
            Log.i(TAG, "showing error message")
            Text(
                text = "Message: ${ request.signupVM.message.value }",
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Red
            )
        }

        TextField(
            value = email1,
            onValueChange = { request.signupVM.email.value = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password1,
            onValueChange = { request.signupVM.password.value = it },
            label = { Text("Password") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Sign in",
            modifier = Modifier
                .clickable(
                    onClick = {
                        NavigationUtil.navigateToSignIn(context = request.signupContext)
                    },
                )
                .padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            if(validateSignupRequest(email1, password1, request.signupVM)) {
                request.signupVM.signUp(email = email1, password = password1, signup = request.signupContext)
                request.signupVM.showErrorMessage = mutableStateOf(false)
            }
        }) {
            Text("Sign Up")
        }
    }

    private fun validateSignupRequest(
        email1: String,
        password1: String,
        signupVM: SignupViewModel,

    ) : Boolean{
        Log.i(TAG, "Signing up with email: $email1, password: $password1")
        if (email1.isBlank() && password1.isBlank()) {
            Log.i(TAG, "Enter all required fields.")
            signupVM.setErrorMessage()
            return false
        } else if (email1.isBlank()) {
            Log.i(TAG, "Email field required.")
            signupVM.message.value = "Email is required."
            signupVM.showErrorMessage = mutableStateOf(true)
            return false
        } else if (password1.isBlank()) {
            Log.i(TAG, "Password field required")
            signupVM.message = mutableStateOf("Password is required.")
            signupVM.showErrorMessage = mutableStateOf(true)
            return false
        }

        if (!StringUtil.validEmail(email1)) {
            signupVM.message = mutableStateOf("Provide a valid email.")
            signupVM.showErrorMessage = mutableStateOf(true)
            return false
        }

        return true
    }
}
