package com.example.fe_funzo.view

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.view_model.SignupViewModel

class AuthenticationFormView (
    private val signupVM : SignupViewModel= SignupViewModel()
) {
    companion object {
        const val TAG = "AuthenticationFormView"
    }

    @Composable
    fun AuthenticationForm(
        signup: Signup
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }


        Text(text = "Hello ${signupVM.name}!", modifier = Modifier.padding(bottom = 16.dp))
        if (signupVM.showErrorMessage.value) {
            Log.i(TAG, "showing error message")
            Text(
                text = "Message: ${ signupVM.message.value }",
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Red
            )
        }

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            signupVM.showErrorMessage.value = signupRequest(email, password, signupVM, signup)
            simulateHotReload(this)
        }) {
            Text("Sign Up")
        }
    }


    private fun signupRequest(
        email1: String,
        password1: String,
        signupVM: SignupViewModel,
        signup: Signup
    ) : Boolean{
        Log.i(TAG, "Signing up with email: $email1, password: $password1")
        if (email1.isBlank() && password1.isBlank()) {
            Log.i(TAG, "Enter all required fields")
            signupVM.setErrorMessage()
            return true
        } else if (email1.isBlank()) {
            Log.i(TAG, "Email field required")
            signupVM.message.value = "Email is required"
            signupVM.showErrorMessage = mutableStateOf(true)
            return true
        } else if (password1.isBlank()) {
            Log.i(TAG, "Password field required")
            signupVM.message = mutableStateOf("Password is required")
            signupVM.showErrorMessage = mutableStateOf(true)
            return true
        }
        signupVM.signUp(email = email1, password = password1, signup)
        return false
    }
}
