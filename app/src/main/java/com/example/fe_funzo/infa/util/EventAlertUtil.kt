package com.example.fe_funzo.infa.util

import android.content.Context
import android.widget.Toast


class EventAlertUtil {
    companion object {
        private const val TAG = "EventAlertUtil"


        fun signupIsSuccessful(context: Context) {
            Toast.makeText(
                context,
                "Authentication success.",
                Toast.LENGTH_SHORT,
            ).show()
        }

        fun signupIsFailed(context: Context) {
            Toast.makeText(
                context,
                "Authentication failed.",
                Toast.LENGTH_SHORT,
            ).show()
        }

    }
}