package com.example.fe_funzo.presentation.activity

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
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme

class StudentDashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context : StudentDashboardActivity = this
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        StudentDashboardScreen(context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun StudentDashboardScreen(context: Context) {
    Text(
        text = "Hello Student Dashboard!",
    )

    Button(onClick = {
        NavigationUtil.navigateToLandingPage(context = context)
    }) {
        Text("Back")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    Fe_funzoTheme {
        StudentDashboardScreen(context = LocalContext.current)
    }
}