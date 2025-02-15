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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.dto.SignupDto
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.AuthFormStrategy
import com.example.fe_funzo.presentation.view.RoleOption
import com.funzo.funzoProxy.domain.user.UserType

class AuthFormSignUpStrategy : AuthFormStrategy<SignupDto> {
    companion object {
        private const val TAG = "AuthenticationFormView"
    }

    @Composable
    override fun Display(request: SignupDto) {
        val email1 = request.signupVM.email.value
        val password1 = request.signupVM.password.value
        val (selectedRole, setSelectedRole) = remember { mutableStateOf<UserType?>(null) }

        Text(text = "Hello ${request.signupVM.name}!", modifier = Modifier.padding(bottom = 16.dp))

        if (request.signupVM.showErrorMessage.value) {
            Log.i(TAG, "showing error message")
            Text(
                text = "Error Message: ${request.signupVM.message.value}",
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Red
            )
        }

        TextField(
            value = email1,
            onValueChange = { request.signupVM.email.value = it.trim() },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password1,
            onValueChange = { request.signupVM.password.value = it.trim() },
            label = { Text("Password") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Select Your Role",
            modifier = Modifier.padding(bottom = 24.dp)
        )

        UserType.entries.forEach { role ->
            RoleOption(
                role = role,
                isSelected = selectedRole == role,
                onSelected = { isSelected ->
                    if (isSelected) {
                        setSelectedRole(role)
                    }
                }
            )
        }

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
            request.signupVM.authenticateUser(
                selectedRole,
                email1,
                password1,
                request.signupContext
            )
        }) {
            Text("Sign Up")
        }
    }
}