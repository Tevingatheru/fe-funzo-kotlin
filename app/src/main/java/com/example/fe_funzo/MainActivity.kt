package com.example.fe_funzo

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.fe_funzo.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.logic.view_model.FirebaseViewModel

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        val firebaseViewModel = FirebaseViewModel()
        val context: Context = this
        firebaseViewModel.validateCurrentUser(context = context)

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)) {
                        NavigateToSignUpPage(context = context)
                    }
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }
}

@Composable
fun NavigateToSignUpPage(context : Context) {
    Log.i("MainActivity", "NavigateToSignUpPage")
    Button(onClick = {
        NavigationUtil.navigateToSignUpActivity(context = context)
    }) {
        Text("Go Sign Up")
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Fe_funzoTheme {
        val context: Context = LocalContext.current
        NavigateToSignUpPage(context)
    }
}
