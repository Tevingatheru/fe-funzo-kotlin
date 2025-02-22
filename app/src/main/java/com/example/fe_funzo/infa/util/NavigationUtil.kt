package com.example.fe_funzo.infa.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.fe_funzo.presentation.activity.CreateExamActivity
import com.example.fe_funzo.presentation.activity.SubjectDetailsActivity
import com.example.fe_funzo.presentation.activity.TeacherDashboardActivity
import com.example.fe_funzo.presentation.activity.ViewExamsActivity
import com.example.fe_funzo.presentation.view.AdminDashboard
import com.example.fe_funzo.presentation.view.AdminLandingPage
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.presentation.view.Signup
import com.example.fe_funzo.presentation.view.TeacherLandingPage
import com.example.fe_funzo.presentation.view.UserProfile
import com.example.fe_funzo.presentation.view.UserProfileSettings
import com.example.fe_funzo.data.model.UserType

class NavigationUtil (){
    companion object {

        private const val TAG = "NavigationUtil"
        private lateinit var intent: Intent

        fun navigateToSignUpActivity(context: Context) {
            Log.i(TAG, "navigateToSignUpActivity")
            intent = Intent(context, Signup::class.java)
            context.startActivity(intent)
        }

        fun navigateToLandingPage(context: Context, userType: UserType) {
            Log.i(TAG, "Navigate to landing page.")
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
            intent = Intent(context, AdminLandingPage::class.java)
            context.startActivity(intent)
        }

        fun navigateToTeacherLandingPage(context: Context) {
            intent = Intent(context, TeacherLandingPage::class.java)
            context.startActivity(intent)
        }

        fun navigateToSignIn(context: Context) {
            Log.i(TAG, "Navigate to sign in page.")
            intent = Intent(context, SignIn::class.java)
            context.startActivity(intent)
        }

        fun navigateToUserProfile(context: Context) {
            Log.i(TAG, "navigateToUserProfile")
            intent = Intent(context, UserProfile::class.java)
            context.startActivity(intent)
        }

        fun navigateToUserProfileSettings(context: Context) {
            Log.i(TAG, "navigateToUserProfileSettings")
            intent = Intent(context, UserProfileSettings::class.java)
            context.startActivity(intent)
        }

        fun navigateToAdminDashboard(context: Context) {
            Log.i(TAG, "navigateToDashboard")
            intent = Intent(context, AdminDashboard::class.java)
            context.startActivity(intent)
        }

        fun navigateToTeacherDashboard(context: Context) {
            Log.i(TAG, "navigateToDashboard")
            intent = Intent(context, TeacherDashboardActivity::class.java)
            context.startActivity(intent)
        }

        fun navigateToCreateExam(context: Context) {
            Log.i(TAG, "navigateToCreateExam")
            intent = Intent(context, CreateExamActivity::class.java)
            context.startActivity(intent)
        }

        fun navigateToViewExams(context: Context) {
            Log.i(TAG, "navigateToViewExams")
            intent = Intent(context, ViewExamsActivity::class.java)
            context.startActivity(intent)
        }

        fun navigateToSubjectDetails(context: Context) {
            Log.i(TAG, "navigateToSubjectDetails")
            intent = Intent(context, SubjectDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
