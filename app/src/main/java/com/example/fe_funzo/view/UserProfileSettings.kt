package com.example.fe_funzo.view

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.infa.util.FirebaseAuthUtil
import com.example.fe_funzo.view.ui.theme.Fe_funzoTheme

class UserProfileSettings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        UserProfileSettingsScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfileSettingsScreen() {
    Text(
        text = "Settings",
    )

    Button(onClick = {
        logout()
    }) {
        Text("Logout")
    }
}

private fun logout() {
    FirebaseAuthUtil.logout()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Fe_funzoTheme {
        UserProfileSettingsScreen()
    }
}
