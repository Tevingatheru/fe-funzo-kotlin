package com.example.fe_funzo.logic.view_model

interface AuthService<T> {
    fun validateAuthFormFields(email: String, password: String, viewModel: T): Boolean

}
