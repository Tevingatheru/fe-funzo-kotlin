package com.example.fe_funzo.logic.service.impl

import com.example.fe_funzo.logic.service.AuthService
import com.example.fe_funzo.logic.view_model.SignInViewModel

class AuthServiceImpl : AuthService<SignInViewModel> {
    override fun validateAuthFormFields(
        email: String,
        password: String,
        viewModel: SignInViewModel
    ): Boolean {
        viewModel.showErrorMessage.value = false
        if (email.isBlank() && password.isBlank()) {
            viewModel.showErrorMessage.value = true
            viewModel.errorMessage.value = "All fields are required"
            return false
        } else if (email.isBlank()) {
            viewModel.showErrorMessage.value = true
            viewModel.errorMessage.value = "Email is required"
            return false
        } else if (password.isBlank()) {
            viewModel.showErrorMessage.value = true
            viewModel.errorMessage.value = "Password is required"
            return false
        }
        viewModel.showErrorMessage.value = false
        return true
    }
}
