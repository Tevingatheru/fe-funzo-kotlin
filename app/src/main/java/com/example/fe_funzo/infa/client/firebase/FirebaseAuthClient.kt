package com.example.fe_funzo.infa.client.firebase

import android.util.Log
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.presentation.view.Signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthClient private constructor(
){
    companion object {
        private const val TAG = "FirebaseAuthUtil"
        private var auth: FirebaseAuth = Firebase.auth

        fun isUserLoggedIn (): Boolean {
            Log.i(TAG, "isUserLoggedIn")
            val currentUser: FirebaseUser? = auth.currentUser
            if (currentUser != null) {
                Log.i(TAG, "currentUser: ${currentUser.email}")
                return true
            } else {
                Log.w(TAG, "No currentUser")
                return false
            }
        }

        fun signUp(email: String, password: String, signup: Signup,
                   isSuccessful: (Boolean) -> Unit)  {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(signup) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Log.i(TAG, "user: $user")
                        EventAlertUtil.signupIsSuccessful(signup)
                        isSuccessful(true)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        EventAlertUtil.signupIsFailed(signup)
                        isSuccessful(false)
                    }
                }
        }

        fun getUserEmail(): String {
            val firebaseUser : FirebaseUser? = auth.currentUser
            Log.i(TAG, "get username of : ${firebaseUser}")
            if (firebaseUser == null) {
                return ""
            }
            return firebaseUser!!.email!!
        }

        fun signIn(email: String, password: String, context: SignIn, success: () -> Unit) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Log.i(TAG, "User details from firebase : ${user}")
                        EventAlertUtil.authenticationSuccess(context)
                        success()
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        EventAlertUtil.authenticationFailure(context)
                    }
                }
        }

        fun logout() {
            auth.signOut()
        }
    }
}
