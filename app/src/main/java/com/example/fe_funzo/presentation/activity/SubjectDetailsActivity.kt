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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.form.SubjectForm
import com.learner.funzo.model.Subject

private const val TAG : String = "SubjectDetailsActivity"

class SubjectDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        Greeting()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val subjectForm: SubjectForm = SubjectForm()
    subjectForm.SubjectDetailsForm { subject: Subject ->
        Log.i(TAG, "Subject: $subject")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    Fe_funzoTheme {
        Greeting()
    }
}
