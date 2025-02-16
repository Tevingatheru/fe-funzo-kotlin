package com.example.fe_funzo.presentation.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.infa.util.NavigationUtil

class UserProfileView {
    companion object {
        private const val TAG = "UserProfileView"
    }

    @Composable
    fun ProfileView(context: Context) {
        Text(
            text = "Profile",
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            NavigationUtil.navigateToUserProfileSettings(context)
        }) {
            Text("Settings")
        }
        Button(onClick = {
            TODO("Call the navigate to landing page strategy based on the user details")
            Log.i(TAG, "Navigate to landing page")
        }) {
            Text("Landing Page")
        }
    }
}
