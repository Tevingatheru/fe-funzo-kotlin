package com.example.fe_funzo.presentation.activity

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import com.example.fe_funzo.logic.view_model.FirebaseViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.view.LandingView

class StudentLandingPage : ComponentActivity() {
    companion object {
        private const val TAG: String = "StudentLandingPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        val context : StudentLandingPage = this
        val landingView: LandingView = LandingView()
        val firebaseViewModel = FirebaseViewModel()
        firebaseViewModel.isUserLoggedOut(this)

        
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        StudentLandingPageScreen()
                        landingView.LandingScreen(context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun StudentLandingPageScreen() {
    Text(text = "Student Portal")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    Fe_funzoTheme {
        StudentLandingPageScreen()
    }
}