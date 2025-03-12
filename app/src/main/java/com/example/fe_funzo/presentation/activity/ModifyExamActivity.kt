package com.example.fe_funzo.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.infa.util.ExamCacheUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme

class ModifyExamActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val exam: Exam = intent.getParcelableExtra("exam", Exam::class.java)!!
        val context: Context = this
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ModifyExamForm(
                            exam = exam,
                            context = context
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ModifyExamForm(exam: Exam, context: Context) {
    Field(exam.subject!!, "Subject:")
    Spacer(modifier = Modifier.height(16.dp))

    Field(exam.description!!, "Description:")
    Spacer(modifier = Modifier.height(16.dp))

    Options(context = context, exam = exam)
}

@Composable
fun Options(context: Context, exam : Exam) {
    Row {
        val modifier = Modifier
            .fillMaxWidth(0.5F)
            .padding(top = 16.dp)
        Button(
            onClick = {
                ExamCacheUtil.setCachedExam(exam = exam, context = context)
                NavigationUtil.navigateToAddQuestionActivity(context = context) },
            modifier = modifier,
        ) {
            Text(
                text = "Add",
            )
        }

        Button(onClick = {
            NavigationUtil.navigateToViewExams(context = context)
        },
            modifier = modifier) {
            Text(text = "Cancel", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
private fun Field(text: String, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = text,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview71() {
    Fe_funzoTheme {
        val exam = Exam(examCode = "code", subject = "subject", description = "description")
        ModifyExamForm(exam = exam, context = LocalContext.current)
    }
}