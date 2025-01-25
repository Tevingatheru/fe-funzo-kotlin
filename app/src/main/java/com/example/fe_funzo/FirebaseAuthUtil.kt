package com.example.fe_funzo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthUtil private constructor(
){
    companion object {
        private const val TAG = "MainActivity"
        private var auth: FirebaseAuth = Firebase.auth
        fun isUserLoggedIn () {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Log.i(TAG, "currentUser: ${currentUser}")
            } else {
                Log.i(TAG, "No currentUser")


//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        Log.d(TAG, "createUserWithEmail:success")
//                        val user = auth.currentUser
//                    } else {
//                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            baseContext,
//                            "Authentication failed.",
//                            Toast.LENGTH_SHORT,
//                        ).show()
//                    }
//                }
            }
        }
    }

}