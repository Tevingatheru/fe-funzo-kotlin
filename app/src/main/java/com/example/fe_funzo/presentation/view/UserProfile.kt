package com.example.fe_funzo.presentation.view

import android.content.Context
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
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme

class UserProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val context = this
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        UserProfileScreen(context)
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfileScreen(context: Context) {
    val userProfileView: UserProfileView = UserProfileView()
    userProfileView.ProfileView(context)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Fe_funzoTheme {
        UserProfileScreen(context = LocalContext.current)
    }
}
