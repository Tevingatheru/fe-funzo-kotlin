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
import com.example.fe_funzo.data.response.CreateSubjectResponse
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.logic.view_model.SubjectViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.form.SubjectForm
import com.example.fe_funzo.data.model.Subject
import com.example.fe_funzo.infa.util.NavigationUtil
import kotlinx.coroutines.runBlocking

private const val TAG : String = "SubjectDetailsActivity"

class SubjectDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        SubjectDetailsScreen(SubjectViewModel(subjectDetailsActivity = context))
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectDetailsScreen(subjectViewModel: SubjectViewModel) {
    val subjectForm: SubjectForm = SubjectForm()
    subjectForm.SubjectDetailsForm { subject: Subject ->
        Log.i(TAG, "Create subject. \n Subject: $subject")
        runBlocking {
            try {
                createSubject(subjectViewModel, subject)
            } catch (e: Exception) {
                EventAlertUtil.createSubjectFailed(subjectDetailsActivity = subjectViewModel.subjectDetailsActivity)
            }
        }
    }
}

private suspend fun createSubject(
    subjectViewModel: SubjectViewModel,
    subject: Subject
) {
    val createSubjectResponse: CreateSubjectResponse =
        subjectViewModel.createSubject(subject = subject)

    EventAlertUtil.createSubjectSuccess(subjectDetailsActivity = subjectViewModel.subjectDetailsActivity)
    NavigationUtil.navigateToViewExams(context = subjectViewModel.subjectDetailsActivity)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    val context = SubjectDetailsActivity()
    Fe_funzoTheme {
        SubjectDetailsScreen(SubjectViewModel(subjectDetailsActivity = context))
    }
}
