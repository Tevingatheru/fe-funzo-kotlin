package com.example.fe_funzo.presentation.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavigationOptions(
    navigateToViewExamsActivity:() -> Unit,
    navigateToDashboardScreen:() -> Unit,
    navigateToProfileScreen:() -> Unit
) {
    Button(onClick = {
        navigateToDashboardScreen()
    }) { Text(text = "Dashboards") }

    Button(onClick = {
        navigateToProfileScreen()
    }) { Text(text = "Profile") }

    Button(onClick = {
        navigateToViewExamsActivity()
    }) { Text(text = "View Exams") }
}
