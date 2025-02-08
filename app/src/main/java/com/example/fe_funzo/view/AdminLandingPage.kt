package com.example.fe_funzo.view

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.infa.client.FirebaseAuthClient
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.view_model.FirebaseViewModel

class AdminLandingPage : ComponentActivity() {
    companion object {
        private const val TAG: String = "AdminLandingPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseViewModel = FirebaseViewModel()
        firebaseViewModel.validateCurrentUser(this)
        val username: String = FirebaseAuthClient.getUsername()
        val context: AdminLandingPage = this
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        LandingScreen(context = context, username = username)
                    }
                }
            }
        }
    }
}

@Composable
fun LandingScreen(context: Context, username: String) {
    Text(
        text = "Welcome ${username}!",

    )
    Button(onClick = {
        navigateToProfileScreen(context)
    }) { Text("Profile") }
}

private fun navigateToProfileScreen(context: Context) {
    NavigationUtil.navigateToUserProfile(context = context)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Fe_funzoTheme {
        LandingScreen(context = LocalContext.current, username = "Android")
    }
}
