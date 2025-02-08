package com.example.fe_funzo.infa.util

import android.util.Log
import android.widget.Toast
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.presentation.view.Signup

class EventAlertUtil {
    companion object {
        private const val TAG = "EventAlertUtil"

        fun signupIsSuccessful(context: Signup) {
            Log.i(TAG, "signupIsSuccessful")
            Toast.makeText(
                context,
                "Authentication success.",
                Toast.LENGTH_SHORT,
            ).show()
        }

        fun signupIsFailed(context: Signup) {
            Log.i(TAG, "signupIsFailed")
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
    }
}
