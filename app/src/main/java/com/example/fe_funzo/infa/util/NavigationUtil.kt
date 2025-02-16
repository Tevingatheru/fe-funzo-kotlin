package com.example.fe_funzo.infa.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.fe_funzo.presentation.activity.TeacherDashboardActivity
import com.example.fe_funzo.presentation.view.AdminDashboard
import com.example.fe_funzo.presentation.view.AdminLandingPage
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.presentation.view.Signup
import com.example.fe_funzo.presentation.view.TeacherLandingPage
import com.example.fe_funzo.presentation.view.UserProfile
import com.example.fe_funzo.presentation.view.UserProfileSettings
import com.funzo.funzoProxy.domain.user.UserType

class NavigationUtil {
    companion object {

        private const val TAG = "NavigationUtil"

        fun navigateToSignUpActivity(context: Context) {
            Log.i(TAG, "navigateToSignUpActivity")
            val intent = Intent(context, Signup::class.java)
            context.startActivity(intent)
        }

        fun navigateToLandingPage(context: Context, userType: UserType) {
            Log.i(TAG, "Navigate to landing page.")
            val intent: Intent
            if (userType == UserType.ADMINISTRATOR) {
                intent = Intent(context, AdminLandingPage::class.java)
            } else if (userType == UserType.TEACHER) {
                intent = Intent(context, TeacherLandingPage::class.java)
            } else {
                throw IllegalArgumentException("UserType does not exist: $userType")
            }
            context.startActivity(intent)
        }

        fun navigateToAdminLandingPage(context: Context) {
            val intent: Intent
            intent = Intent(context, AdminLandingPage::class.java)
            context.startActivity(intent)
        }

        fun navigateToTeacherLandingPage(context: Context) {
            val intent: Intent
            intent = Intent(context, TeacherLandingPage::class.java)
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

        fun navigateToAdminDashboard(context: Context) {
            Log.i(TAG, "navigateToDashboard")
            val intent: Intent = Intent(context, AdminDashboard::class.java)
            context.startActivity(intent)
        }

        fun navigateToTeacherDashboard(context: Context) {
            Log.i(TAG, "navigateToDashboard")
            val intent: Intent = Intent(context, TeacherDashboardActivity::class.java)
            context.startActivity(intent)
        }
    }
}
