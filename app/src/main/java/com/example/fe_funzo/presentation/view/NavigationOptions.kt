package com.example.fe_funzo.presentation.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fe_funzo.data.model.UserType

@Composable
fun NavigationOptions(
    navigateToViewExamsActivity:() -> Unit,
    navigateToDashboardScreen:() -> Unit,
    navigateToProfileScreen:() -> Unit,
    userType: UserType
) {
    Button(onClick = {
        navigateToDashboardScreen()
    }) { Text(text = "Dashboards") }

    Button(onClick = {
        navigateToProfileScreen()
    }) { Text(text = "Profile") }
    if (userType != UserType.ADMINISTRATOR) {
        Button(onClick = {
            navigateToViewExamsActivity()
        }) { Text(text = "View Exams") }
    }
}
