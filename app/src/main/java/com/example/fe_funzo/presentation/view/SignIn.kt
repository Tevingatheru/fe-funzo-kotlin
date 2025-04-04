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
import com.example.fe_funzo.logic.view_model.FirebaseViewModel
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.logic.view_model.SignInViewModel
import com.example.fe_funzo.presentation.AuthFormSignInStrategy

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
        val firebaseViewModel: FirebaseViewModel = FirebaseViewModel()
        firebaseViewModel.isUserLoggedIn(context = this)
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