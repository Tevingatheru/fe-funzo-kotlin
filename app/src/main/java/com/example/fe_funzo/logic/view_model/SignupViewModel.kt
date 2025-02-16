package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.view.Signup
import com.funzo.funzoProxy.domain.user.UserType
import kotlinx.coroutines.runBlocking

class SignupViewModel(
    var email: MutableState<String>  = mutableStateOf(""),
    var password : MutableState<String>  = mutableStateOf(""),
    var showErrorMessage: MutableState<Boolean> = mutableStateOf(false),
    var name:String  = "",
    var message: MutableState<String> = mutableStateOf(""),
) : ViewModel() {

    companion object {
        private const val TAG = "SignupViewModel"
    }

    private fun setErrorMessage() {
        message = mutableStateOf("Email and password are required")
        showErrorMessage = mutableStateOf(true)
        Log.i(TAG, "Message: ${message.value}, show: ${showErrorMessage}")
    }

    private fun signUp(email: String, password: String, signup: Signup, selectedRole:UserType) {
        Log.i(TAG, "signUp")
        runBlocking {
            FirebaseAuthClient.signUp(email=email, password=password, signup=signup) { isSuccessful ->
                if (isSuccessful) {
                    NavigationUtil.navigateToLandingPage(selectedRole, signup)

                    try {
                        val userRepoServiceImpl = UserRepoServiceImpl(context = signup)
                        userRepoServiceImpl.save(selectedRole, email=  email)
                    } catch (e:Exception) {
                        Log.e(TAG, "Error: ${e.message}")
                    }
                } else {
                    Log.i(TAG, "Sign up failed")
                }
            }
        }
    }

    fun authenticateUser(selectedRole: UserType?, email: String, password: String, signupContext: Signup) {
        Log.i(TAG, "selectedRole: $selectedRole")

        if (selectedRole == null) {
            Log.i(TAG, "selectedRole is null")
            EventAlertUtil.signupIsFailed(context = signupContext)
            message.value = "Select a role"
            showErrorMessage.value = true
            return
        }

        if (validateSignupRequest(email, password, this)) {
            signUp(
                email = email,
                password = password,
                signup = signupContext,
                selectedRole = selectedRole,
            )
            showErrorMessage = mutableStateOf(false)
        }
    }

    private fun validateSignupRequest(
        email1: String,
        password1: String,
        signupVM: SignupViewModel,

        ) : Boolean{

        Log.i(TAG, "Signing up with email: $email1, password: $password1")

        if (email1.isBlank() && password1.isBlank()) {
            Log.i(TAG, "Enter all required fields.")
            signupVM.setErrorMessage()
            return false
        } else if (email1.isBlank()) {
            Log.i(TAG, "Email field required.")
            signupVM.message.value = "Email is required."
            signupVM.showErrorMessage = mutableStateOf(true)
            return false
        } else if (password1.isBlank()) {
            Log.i(TAG, "Password field required")
            signupVM.message = mutableStateOf("Password is required.")
            signupVM.showErrorMessage = mutableStateOf(true)
            return false
        }

        if (!StringUtil.validEmail(email1)) {
            signupVM.message = mutableStateOf("Provide a valid email.")
            signupVM.showErrorMessage = mutableStateOf(true)
            return false
        }

        return true
    }
}
