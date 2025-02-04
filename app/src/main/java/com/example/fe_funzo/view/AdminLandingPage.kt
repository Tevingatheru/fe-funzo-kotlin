package com.example.fe_funzo.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.NavigateToSignUpPage
import com.example.fe_funzo.infa.util.FirebaseAuthUtil
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
        val username: String = FirebaseAuthUtil.getUsername()
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LandingScreen(
                        name = username,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LandingScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Button(onClick = {
        navigateToProfileScreen()
    }) { Text("Profile") }
}

private fun navigateToProfileScreen() {
    NavigationUtil.navigateToUserProfile()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Fe_funzoTheme {
        LandingScreen("Android")
    }
}