package com.example.fe_funzo.view

import android.os.Bundle
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.ui.theme.Fe_funzoTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

const val name = "Signup"
class Signup : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SignupView(email, password)
                    }
                }
            }
        }
    }
}

@Composable
private fun SignupView(email: String, password: String) {
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
        // Call your signup function here with email and password
        println("Signing up with email: $email1, password: $password1")
    }) {
        Text("Sign Up")
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Fe_funzoTheme {
        SignupView(email= "email", password= "password")
    }
}
