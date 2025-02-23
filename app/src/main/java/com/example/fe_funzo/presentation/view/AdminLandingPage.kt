package com.example.fe_funzo.presentation.view

import android.os.Bundle
import android.util.Log
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
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.logic.view_model.FirebaseViewModel

class AdminLandingPage : ComponentActivity() {
    companion object {
        private const val TAG: String = "AdminLandingPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        val firebaseViewModel = FirebaseViewModel()
        firebaseViewModel.isUserLoggedOut(this)
        val username: String = FirebaseAuthClient.getUserEmail()

        val context: AdminLandingPage = this

        firebaseViewModel.isUserLoggedOut(context = this)

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

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
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
