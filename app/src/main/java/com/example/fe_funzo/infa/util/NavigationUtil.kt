package com.example.fe_funzo.infa.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.fe_funzo.view.AdminDashboards
import com.example.fe_funzo.view.AdminLandingPage
import com.example.fe_funzo.view.SignIn
import com.example.fe_funzo.view.Signup
import com.example.fe_funzo.view.UserProfile
import com.example.fe_funzo.view.UserProfileSettings

class NavigationUtil {
    companion object {
        private const val TAG = "NavigationUtil"
        fun navigateToSignUpActivity(context: Context) {
            Log.i(TAG, "navigateToSignUpActivity")
            val intent = Intent(context, Signup::class.java)
            context.startActivity(intent)
        }

        fun navigateToLandingPage(context: Context) {
            Log.i(TAG, "Navigate to admin landing page.")
            val intent = Intent(context, AdminLandingPage::class.java)
            context.startActivity(intent)
        }

        fun navigateToSignIn(context: Context) {
            Log.i(TAG, "Navigate to sign in page.")
            val intent = Intent(context, SignIn::class.java)
            context.startActivity(intent)
        }

        fun navigateToUserProfile(context: Context) {
            Log.i(TAG, "navigateToUserProfile")
            val intent: Intent = Intent(context, UserProfile::class.java)
            context.startActivity(intent)
        }

        fun navigateToUserProfileSettings(context: Context) {
            Log.i(TAG, "navigateToUserProfileSettings")
            val intent: Intent = Intent(context, UserProfileSettings::class.java)
            context.startActivity(intent)
        }

        fun navigateToDashboard(context: Context) {
            Log.i(TAG, "navigateToDashboard")
            val intent: Intent = Intent(context, AdminDashboards::class.java)
            context.startActivity(intent)
        }
    }
}
