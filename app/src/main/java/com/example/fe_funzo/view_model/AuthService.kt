package com.example.fe_funzo.view_model

import androidx.lifecycle.ViewModel

interface AuthService<T> {
    fun validateAuthFormFields(email: String, password: String, viewModel: T): Boolean

}
