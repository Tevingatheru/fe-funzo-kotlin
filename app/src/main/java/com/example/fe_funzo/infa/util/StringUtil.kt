package com.example.fe_funzo.infa.util

import android.util.Log
import com.example.fe_funzo.view_model.AuthFormSignUpStrategy.Companion.TAG

class StringUtil {
    companion object {
        fun validEmail(email: String): Boolean {
            Log.i(TAG, "Validate email : ${email}.")
            val emailRegex = """^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
            val matches: Boolean = emailRegex.matches(email.trim())
            Log.i(TAG, "Validate match : ${matches}.")
            return matches
        }
    }
}
