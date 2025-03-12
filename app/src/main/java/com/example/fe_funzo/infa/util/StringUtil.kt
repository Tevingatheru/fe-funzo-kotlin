package com.example.fe_funzo.infa.util

import android.util.Log

object StringUtil {

    private const val TAG:String = "StringUtil"
    const val EXAM_KEY = "exam"
    const val QUESTION_KEY = "question"

    fun validEmail(email: String): Boolean {
        val emailRegex = """^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
        val matches: Boolean = emailRegex.matches(email)

        Log.i(TAG, "Validate match : ${matches}.")

        return matches
    }
}
