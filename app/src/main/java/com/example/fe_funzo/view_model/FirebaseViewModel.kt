package com.example.fe_funzo.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.util.FirebaseAuthUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.view.Signup

class FirebaseViewModel: ViewModel() {
    companion object {
        private const val TAG = "FirebaseViewModel"
    }

    fun validateCurrentUser(context: Context) {
        if (!FirebaseAuthUtil.isUserLoggedIn()) {
            NavigationUtil.navigateToSignUpActivity(context)
        }
    }


}