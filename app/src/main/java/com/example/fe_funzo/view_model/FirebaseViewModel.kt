package com.example.fe_funzo.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.util.FirebaseAuthUtil
import com.example.fe_funzo.infa.util.NavigationUtil

class FirebaseViewModel: ViewModel() {
    fun validateCurrentUser(context: Context) {
        if (!FirebaseAuthUtil.isUserLoggedIn()) {
            NavigationUtil.navigateToSignUpActivity(context)
        }
    }
}