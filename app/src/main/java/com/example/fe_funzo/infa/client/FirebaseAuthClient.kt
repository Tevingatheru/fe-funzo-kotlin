package com.example.fe_funzo.infa.client

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
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Log.i(TAG, "currentUser: ${currentUser}")
                return true
            } else {
                Log.w(TAG, "No currentUser")
                return false
            }
        }

        fun signUp(email: String, password: String, signup: Signup,
                   isSuccessful: (Boolean) -> Unit)  {
            Log.i(TAG, "createUserWithEmail")

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(signup) { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Log.i(TAG, "user: ${user}")
                        EventAlertUtil.signupIsSuccessful(signup)
                        isSuccessful(true)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        EventAlertUtil.signupIsFailed(signup)
                        isSuccessful(false)
                    }
                }
        }

        fun getUsername(): String {
            val firebaseUser : FirebaseUser? = auth.currentUser
            Log.i(TAG, "get username of : ${firebaseUser}")
            return firebaseUser!!.email!!
        }

        fun signIn(email: String, password: String, context: SignIn, callback: (Boolean) -> Unit) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Log.i(TAG, "user : ${user}")
                        callback(true)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        EventAlertUtil.authenticationFailure(context)
                        callback(false)
                    }
                }
        }

        fun logout() {
            auth.signOut()
        }
    }
}
