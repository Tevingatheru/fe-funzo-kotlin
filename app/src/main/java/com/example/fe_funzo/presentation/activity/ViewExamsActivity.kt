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
import com.example.fe_funzo.logic.view_model.ExamViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.learner.funzo.model.Exam

class ViewExamsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val examViewModel: ExamViewModel = ExamViewModel()
        val context: Context = this
        val exams: List<Exam> = examViewModel.getExamsByTeacher(context = context)

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding),){
                        ViewExamsView(
                            name = "View Exams",
                            context= context
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ViewExamsView(name: String,  context: Context) {
    Text(
        text = "Hello $name!",
    )
    Button(onClick = {
        NavigationUtil.navigateToSubjectDetails(context = context)
    }) {
        Text("Create Subject")
    }
}

@Preview(showBackground = true)
@Composable
fun Preview7() {
    Fe_funzoTheme {
        ViewExamsView("Android", LocalContext.current)
    }
}
