package com.example.fe_funzo.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.client.FirebaseAuthClient
import com.example.fe_funzo.infa.util.NavigationUtil

class FirebaseViewModel: ViewModel() {
    companion object {
        private const val TAG = "FirebaseViewModel"
    }

    fun validateCurrentUser(context: Context) {
        Log.i(TAG, "validateCurrentUser")
        if (!FirebaseAuthClient.isUserLoggedIn()) {
            Log.i(TAG, "validateCurrentUser: true")
            NavigationUtil.navigateToSignUpActivity(context)
        }
    }

    fun logout(context: Context) {
        FirebaseAuthClient.logout()
        NavigationUtil.navigateToSignUpActivity(context = context)
    }
}
