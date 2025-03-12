package com.example.fe_funzo.presentation.activity

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.data.form.ExamForm
import com.example.fe_funzo.data.retrofit.request.CreateExamRequest
import com.example.fe_funzo.data.retrofit.response.CreateExamResponse
import com.example.fe_funzo.logic.service.client.impl.ExamClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.logic.view_model.ExamViewModel
import com.example.fe_funzo.logic.view_model.SubjectViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.form.ExamFormComponent
import kotlinx.coroutines.runBlocking

private const val TAG = "CreateExamActivity"

class CreateExamActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        val context: Context = this

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        CreateExamView(context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun CreateExamView(context: Context) {
    val examViewModel : ExamViewModel = ExamViewModel(formData = ExamForm())
    val subjectViewModel:  SubjectViewModel = SubjectViewModel()

    ExamFormComponent(subjectViewModel = subjectViewModel,
        examViewModel = examViewModel,
        onSubmit = {
            createExam(context, subjectViewModel, examViewModel)
        },
        subjectList = subjectViewModel.getSubjectList(),
        context = context
    )
}


private fun createExam(
    context: Context,
    subjectViewModel: SubjectViewModel,
    examViewModel: ExamViewModel
) {
    Log.i(TAG, "Create exam.")
    val examClient: ExamClientServiceImpl = ExamClientServiceImpl()
    val userRepoServiceImpl = UserRepoServiceImpl(context = context)
    val userCode: String = userRepoServiceImpl.getFirstUser().userCode

    val createExamRequest: CreateExamRequest = CreateExamRequest(
        userCode = userCode,
        subjectCode = subjectViewModel.getSubjectCode(),
        examDescription = examViewModel.getExamDescription(),
    )

    try {
        runBlocking {
            Log.i(TAG, "CreateExamRequest: $createExamRequest")
            val createExamResponse: CreateExamResponse = examClient
                .createExam(createExamRequest = createExamRequest)
        }
    } catch (e: Exception) {
        Log.e(TAG, "Error in creating an exam. Error: $e")
    }
}

@Preview(showBackground = true)
@Composable
fun Preview6() {
    val context: Context = LocalContext.current

    Fe_funzoTheme {
        CreateExamView(context = context)
    }
}
