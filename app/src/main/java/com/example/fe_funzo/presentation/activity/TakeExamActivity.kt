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
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme

class TakeExamActivity : ComponentActivity() {
    companion object {
        private const val TAG: String = "TakeExamActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        TakeExamScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun TakeExamScreen() {
    Text(
        text = "Welcome to take exam page"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    Fe_funzoTheme {
        TakeExamScreen()
    }
}