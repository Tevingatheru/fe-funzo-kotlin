package com.example.fe_funzo.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.infa.client.firebase.FirebaseAuthClient
import com.example.fe_funzo.logic.view_model.FirebaseViewModel
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme

class TeacherLandingPage : ComponentActivity() {
    companion object {
        private const val TAG = "TeacherLandingPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseViewModel = FirebaseViewModel()
        firebaseViewModel.isUserLoggedOut(this)
        val landingView: LandingView = LandingView()
        val context: Context = this
        val userEmail: String = FirebaseAuthClient.getUserEmail()

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        Text("Teacher Portal")
                        landingView.LandingScreen(context = context, username = userEmail)
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
fun GreetingPreview() {
    val landingView: LandingView = LandingView()
    Fe_funzoTheme {
        landingView.LandingScreen(context = LocalContext.current, username = "Teacher")
    }
}