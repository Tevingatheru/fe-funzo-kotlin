package com.example.fe_funzo.infa.util

import android.widget.Toast
import com.example.fe_funzo.presentation.activity.SubjectDetailsActivity
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.presentation.view.Signup

class EventAlertUtil {
    companion object {
        private const val TAG = "EventAlertUtil"

        fun signupIsSuccessful(context: Signup) {
            Toast.makeText(
                context,
                "Authentication success.",
                Toast.LENGTH_SHORT,
            ).show()
        }

        fun signupIsFailed(context: Signup) {
            Toast.makeText(
                context,
                "Authentication failed.",
                Toast.LENGTH_SHORT,
            ).show()
        }

        fun authenticationFailure(context: SignIn) {
            Toast.makeText(
                context,
                "Authentication failed.",
                Toast.LENGTH_SHORT,
            ).show()
        }

        fun signInHasFailed(context: SignIn) {
            Toast.makeText(
                context,
                "Authentication failed.",
                Toast.LENGTH_SHORT,
            ).show()
        }

        fun createSubjectSuccess(subjectDetailsActivity: SubjectDetailsActivity) {
            Toast.makeText(
                subjectDetailsActivity,
                "Subject Created.",
                Toast.LENGTH_SHORT,
            ).show()
        }

        fun createSubjectFailed(subjectDetailsActivity: SubjectDetailsActivity) {
            Toast.makeText(
                subjectDetailsActivity,
                "Create Subject Request Failed.",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}
