package com.example.fe_funzo.presentation.view

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fe_funzo.infa.util.NavigationUtil

class LandingView {
    @Composable
    fun LandingScreen(context: Context, username: String) {
        Text(
            text = "Welcome ${username}!",
        )
        NavigationOptions(context)
    }

    @Composable
    private fun NavigationOptions(context: Context) {
        Button(onClick = {
            navigateToDashboardScreen(context)
        }) { Text(text = "Dashboards") }
        Button(onClick = {
            navigateToProfileScreen(context)
        }) { Text(text = "Profile") }
    }

    private fun navigateToDashboardScreen(context: Context) {
        NavigationUtil.navigateToDashboard(context=context)
    }

    private fun navigateToProfileScreen(context: Context) {
        NavigationUtil.navigateToUserProfile(context = context)
    }
}
