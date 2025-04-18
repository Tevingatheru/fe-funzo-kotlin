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
import com.example.fe_funzo.data.dto.SignupDto
import com.example.fe_funzo.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.logic.view_model.SignupViewModel
import com.example.fe_funzo.presentation.AuthFormSignUpStrategy

private const val TAG = "Signup"

class Signup : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val signupContext = this
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
                        SignupView( signupContext)
                    }
                }
            }
        }
    }

    public override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    public override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    public override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    public override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    public override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }
}

@Composable
private fun SignupView(signup: Signup) {
    val authStrategyContext: AuthFormDisplayContext<SignupDto> = AuthFormDisplayContext<SignupDto>()
    authStrategyContext.setStrategy(AuthFormSignUpStrategy())
    authStrategyContext.Display(SignupDto(signupVM = SignupViewModel(), signupContext = signup))
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Fe_funzoTheme {
        SignupView( signup = Signup())
    }
}
