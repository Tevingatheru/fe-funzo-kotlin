package com.example.fe_funzo.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.infa.client.FirebaseAuthClient
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.logic.view_model.FirebaseViewModel

class AdminLandingPage : ComponentActivity() {
    companion object {
        private const val TAG: String = "AdminLandingPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseViewModel = FirebaseViewModel()
        firebaseViewModel.validateCurrentUser(this)
        val username: String = FirebaseAuthClient.getUserEmail()

        val context: AdminLandingPage = this

        firebaseViewModel.validateCurrentUser(context = this)

        val landingView: LandingView = LandingView()
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        landingView.LandingScreen(context = context, username = username)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview1() {
    val landingView: LandingView = LandingView()
    Fe_funzoTheme {
        landingView.LandingScreen(context = LocalContext.current, username = "Android")
    }
}
