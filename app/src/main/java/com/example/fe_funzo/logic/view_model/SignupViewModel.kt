package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.room.request.CreateUserRequest
import com.example.fe_funzo.data.room.response.UserResponse
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.UserClient
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.service.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.view.Signup
import com.example.fe_funzo.data.model.UserType
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

    private fun signUp(email: String, password: String, signup: Signup, selectedRole: UserType) {
        Log.i(TAG, "signUp")
        val userClient: UserClient =
            RetrofitClientBuilder.build(serviceClass = UserClient::class.java)
        val userService: UserClientServiceImpl = UserClientServiceImpl(userClient = userClient)

        runBlocking {
            val response: UserResponse = cacheUserDetails(userService, selectedRole, email)
            signupViaFirebase(email = email, password = password, signup = signup,
                selectedRole =  selectedRole, userRepoServiceImpl = UserRepoServiceImpl(context = signup),
                response= response)
        }
    }

    private fun signupViaFirebase(
        email: String,
        password: String,
        signup: Signup,
        selectedRole: UserType,
        userRepoServiceImpl: UserRepoServiceImpl,
        response: UserResponse
    ) {
        FirebaseAuthClient.signUp(
            email = email,
            password = password,
            signup = signup
        ) { isSuccessful ->
            if (isSuccessful) {
                NavigationUtil.navigateToLandingPage(userType = selectedRole, context = signup)

                try {
                    userRepoServiceImpl.save(email = email, response = response)
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to save user. Error: $e")
                }
            } else {
                Log.e(TAG, "Sign up failed")
            }
        }
    }

    private suspend fun cacheUserDetails(
        userService: UserClientServiceImpl,
        selectedRole: UserType,
        email: String
    ) = userService.createUser(
        request = CreateUserRequest(selectedRole.type!!, email)
    )

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
            signupVM.setErrorMessage()
            return false
        } else if (email1.isBlank()) {
            signupVM.message.value = "Email is required."
            signupVM.showErrorMessage = mutableStateOf(true)
            return false
        } else if (password1.isBlank()) {
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
