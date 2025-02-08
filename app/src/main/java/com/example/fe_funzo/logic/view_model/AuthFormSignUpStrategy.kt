package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.dto.SignupRequest
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.funzo.funzoProxy.domain.user.UserType

class AuthFormSignUpStrategy : AuthFormStrategy<SignupRequest> {
    companion object {
        private const val TAG = "AuthenticationFormView"
    }

    @Composable
    override fun Display(request: SignupRequest) {
        val email1 = request.signupVM.email.value
        val password1 = request.signupVM.password.value
        val (selectedRole, setSelectedRole) = remember { mutableStateOf<UserType?>(null) }

        Text(text = "Hello ${request.signupVM.name}!", modifier = Modifier.padding(bottom = 16.dp))

        if (request.signupVM.showErrorMessage.value) {
            Log.i(TAG, "showing error message")
            Text(
                text = "Error Message: ${ request.signupVM.message.value }",
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
            request.signupVM.authenticateUser(selectedRole, email1, password1, request.signupContext)
        }) {
            Text("Sign Up")
        }
    }
}

@Composable
private fun RoleOption(
    role: UserType,
    isSelected: Boolean,
    onSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelected(true) },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.outline
            )
        )
        Text(
            text = role.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}
