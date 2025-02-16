package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.infa.client.retrofit.RetrofitClient
import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.mapper.UserMapper
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.UserRepoService
import com.example.fe_funzo.logic.service.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.view.SignIn
import com.funzo.funzoProxy.domain.user.UserType
import kotlinx.coroutines.runBlocking

class SignInViewModel (var showErrorMessage: MutableState<Boolean> = mutableStateOf(false),
                       var errorMessage: MutableState<String> = mutableStateOf(""),
                       var email: MutableState<String> = mutableStateOf(""),
                       var password: MutableState<String> = mutableStateOf("")) {

    companion object {
        const val TAG: String = "SignInViewModel"
    }

    fun signIn(email: String, password: String, signInContext: SignIn) {
        FirebaseAuthClient.signIn(email, password, context = signInContext) {

            if(it) {
                Log.i(TAG, "sign in callback success")
                val userClient: UserClient =
                    RetrofitClient.createClient(serviceClass = UserClient::class.java)
                val userService: UserClientServiceImpl = UserClientServiceImpl(userClient = userClient)

                runBlocking {
                    val createUserResponse = userService.getUserByEmail(email)
                    val user: User = UserMapper.mapCreateUserResponse(createUserResponse)
                    val userType = UserType.find(user.userType)

                    UserRepoServiceImpl(context = signInContext)
                        .save(userType = userType, email = email, response = createUserResponse)
                    NavigationUtil.navigateToLandingPage(
                        context = signInContext,
                        userType = userType
                    )
                }
            } else {
                Log.e(TAG, "sign in callback failure")
            }
        }
    }

}
