package com.example.fe_funzo.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.view_model.ExamListViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.view.ExamViewScreen

private const val TAG : String = "ViewExamsActivity"

class ViewExamsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this
        val examViewScreen: ExamViewScreen = ExamViewScreen()
        val examListViewModel: ExamListViewModel = ExamListViewModel()
        val examListResponse: List<Exam> = examListViewModel.getExamList(context = context)

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        ViewExamsViewHeader(
                            context = context,
                        )
                        examViewScreen.ExamListView(examListResponse = examListResponse, context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun ViewExamsViewHeader(context: Context) {
    Text(
        text = "List of Created Exams",
    )

    Spacer(modifier = Modifier.height(16.dp))

    NavigationPanel(context)
}

@Composable
private fun NavigationPanel(context: Context) {
    Button(onClick = {
        NavigationUtil.navigateToSubjectDetails(context = context)
    }) {
        Text("Create Subject")
    }

    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        NavigationUtil.navigateToCreateExam(context = context)
    }) {
        Text("Create Exam")
    }

    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        NavigationUtil.navigateToLandingPage(context = context)
    }) {
        Text("Back")
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun Preview7() {
    Fe_funzoTheme {
        ViewExamsViewHeader(context = LocalContext.current)
    }
}
