package com.example.fe_funzo.logic.service

interface AuthService<T> {
    fun validateAuthFormFields(email: String, password: String, viewModel: T): Boolean

}
