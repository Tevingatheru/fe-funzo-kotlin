package com.example.fe_funzo.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.ui.theme.Fe_funzoTheme
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.view_model.SignupViewModel

var context: Signup = Signup()

class Signup : ComponentActivity() {
    companion object {
        private const val TAG = "ComponentActivity"
    }
    private val signupVM = SignupViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        context = this
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SignupView(signupVM.email, signupVM.password, signupVM)
                    }
                }
            }
        }
    }
}

private const val name = "Signup"
private const val TAG = "ComponentActivityClass"

@Composable
private fun SignupView(email: String, password: String, signupVM: SignupViewModel) {
    var email1 = email
    var password1 = password
    Text(text = "Hello $name!", modifier = Modifier.padding(bottom = 16.dp))
    TextField(
        value = email1,
        onValueChange = { email1 = it },
        label = { "Email" },
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        value = password1,
        onValueChange = { password1 = it },
        label = { "Password" },
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        Log.i(TAG,"Signing up with email: $email1, password: $password1")
        signupVM.signUp(email=email1, password=password1, context)
    }) {
        Text("Sign Up")
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Fe_funzoTheme {
        SignupView(email= "email", password= "password", signupVM=SignupViewModel())
    }
}
