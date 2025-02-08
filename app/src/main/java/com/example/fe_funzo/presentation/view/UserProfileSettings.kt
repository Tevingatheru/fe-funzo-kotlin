package com.example.fe_funzo.presentation.view

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
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.logic.view_model.FirebaseViewModel

class UserProfileSettings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val context = this
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        UserProfileSettingsScreen(context)
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfileSettingsScreen(context: Context) {
    Text(
        text = "Settings",
    )

    Button(onClick = {
        logout(context)
    }) {
        Text("Logout")
    }
}

private fun logout(context: Context) {
    val firebaseViewModel: FirebaseViewModel = FirebaseViewModel()
    firebaseViewModel.logout(context = context)

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Fe_funzoTheme {
        UserProfileSettingsScreen(context = LocalContext.current)
    }
}
