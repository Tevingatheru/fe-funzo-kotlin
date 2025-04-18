package com.example.fe_funzo.presentation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.dto.SignInDto
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.service.impl.AuthServiceImpl
import com.example.fe_funzo.logic.strategy.AuthFormStrategy
import com.example.fe_funzo.logic.view_model.SignInViewModel
import com.example.fe_funzo.presentation.view.SignIn

class AuthFormSignInStrategy : AuthFormStrategy<SignInDto> {
    companion object {
        const val TAG: String = "AuthFormSignInStrategy"
    }

    @Composable
    override fun Display(request: SignInDto) {
        val email1 = request.signInVM.email.value.trim()
        val password1 = request.signInVM.password.value

        Text(text = "Sign in page", modifier = Modifier.padding(bottom = 16.dp))
        if (request.signInVM.showErrorMessage.value) {
            Log.i(TAG, "showing error message")
            Text(
                text = "Message: ${request.signInVM.errorMessage.value}",
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Red,
            )
        }

        TextField(
            value = email1,
            onValueChange = { request.signInVM.email.value = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password1,
            onValueChange = { request.signInVM.password.value = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Sign up",
            modifier = Modifier
                .clickable(
                    onClick = {
                        NavigationUtil.navigateToSignUpActivity(request.signInContext)
                    },
                )
                .padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            signIn(request, email1, password1)
        }) {
            Text("Sign In")
        }
    }

    private fun signIn(request: SignInDto, email1: String, password1: String) {
        Log.i(TAG, "Sign in button clicked")
        request.signInVM.showErrorMessage.value = false
        val authServiceImpl = AuthServiceImpl()

        authServiceImpl.validateAuthFormFields(
            email1,
            password1,
            request.signInVM
        )

        if (StringUtil.validEmail(email = email1)) {
            val signInViewModel = SignInViewModel()
            signInViewModel.signIn(email1, password1, request.signInContext)
        } else {
            request.signInVM.errorMessage.value = "Enter a valid email"
            request.signInVM.showErrorMessage.value = true
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        Display(SignInDto(signInContext = SignIn()))
    }
}