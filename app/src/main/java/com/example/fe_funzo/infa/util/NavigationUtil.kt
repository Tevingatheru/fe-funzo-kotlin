package com.example.fe_funzo.infa.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.fe_funzo.view.Signup

class NavigationUtil {
    companion object {
        private const val TAG = "NavigationUtil"
        fun navigateToSignUpActivity(context: Context) {
            Log.i(TAG, "navigateToSignUpActivity")
            val intent = Intent(context, Signup::class.java)
            context.startActivity(intent)
        }

        fun navigateToLandingPage() {
            TODO("Not yet implemented")
        }
    }
}