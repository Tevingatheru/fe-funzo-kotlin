package com.example.fe_funzo.infa.util

import android.util.Log
import com.example.fe_funzo.view.Signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthUtil private constructor(
){
    companion object {
        private const val TAG = "MainActivity"
        private var auth: FirebaseAuth = Firebase.auth

        fun isUserLoggedIn (): Boolean {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Log.i(TAG, "currentUser: ${currentUser}")
                return true
            } else {
                Log.w(TAG, "No currentUser")
                return false
            }
        }

        fun signUp(email: String, password: String, context: Signup) : Boolean {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Log.i(TAG, "user: ${user}")
                        EventAlertUtil.signupIsSuccessful(context)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        EventAlertUtil.signupIsFailed(context)
                    }
                }
            return true
        }
    }
}
