package com.example.fe_funzo.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.data.dto.SignInDto
import com.example.fe_funzo.data.model.UserType
import com.example.fe_funzo.data.response.UserResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.UserClient
import com.example.fe_funzo.infa.client.room.FunzoDatabase
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.client.room.UserRepository
import com.example.fe_funzo.logic.service.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.logic.view_model.FirebaseViewModel
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.logic.view_model.SignInViewModel
import com.example.fe_funzo.presentation.AuthFormSignInStrategy
import kotlinx.coroutines.runBlocking

class SignIn : ComponentActivity() {
    companion object {
        private const val TAG = "SignIn"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .clickable {  },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        ) {
                        SignInScreen(
                            context = context
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
//        FirebaseAuthClient.isUserLoggedIn()
        val firebaseViewModel: FirebaseViewModel = FirebaseViewModel()
        firebaseViewModel.isUserLoggedIn(context = this, userType = getUserType())
    }

    private fun getUserType(): UserType {
        val userRepository: UserRepoServiceImpl = UserRepoServiceImpl(context = this)

        runBlocking {
            val user: User = userRepository.getFirstUser()
            val userType = user.userType
            return userType
        }

    }

}

@Composable
fun SignInScreen(context: SignIn) {
    val authFormStrategyContext: AuthFormDisplayContext<SignInDto> = AuthFormDisplayContext<SignInDto>()
    authFormStrategyContext.setStrategy(AuthFormSignInStrategy())
    authFormStrategyContext.Display(SignInDto(SignInViewModel(), signInContext = context))
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Fe_funzoTheme {
        SignInScreen(context = SignIn())
    }
}