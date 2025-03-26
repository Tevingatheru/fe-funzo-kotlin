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
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.model.Option
import com.example.fe_funzo.data.model.OptionType
import com.example.fe_funzo.data.retrofit.response.ExamContentResponse
import com.example.fe_funzo.data.retrofit.response.QuestionContentResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.ExamClient
import com.example.fe_funzo.infa.mapper.OptionMapper
import com.example.fe_funzo.infa.util.ExamCacheUtil
import com.example.fe_funzo.logic.service.client.impl.ExamClientServiceImpl
import com.example.fe_funzo.logic.view_model.TakeExamViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.view.TrueFalseQuizScreen
import kotlinx.coroutines.runBlocking

class TakeExamActivity : ComponentActivity() {
    companion object {
        internal const val TAG: String = "TakeExamActivity"
    }
    val takeExamViewModel: TakeExamViewModel = TakeExamViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        val context: TakeExamActivity = this
        val exam: Exam = ExamCacheUtil.getCachedExam(context = context)
        val questionClient = RetrofitClientBuilder.build(ExamClient::class.java)
        val examClientServiceImpl = ExamClientServiceImpl(examClient = questionClient)

        takeExamViewModel.setTakeExamActivityContext(context = context)
        runBlocking {
            val examContentResponse : ExamContentResponse = examClientServiceImpl.getExamContentByExamCode(examCode = exam.examCode!!)
            takeExamViewModel.setExamContentResponse(examContentResponse = examContentResponse)
            takeExamViewModel.setQuestions(questions = examContentResponse.questions)
        }

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

    @Composable
    fun TakeExamScreen() {
        takeExamViewModel.DisplayQuestion()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    Fe_funzoTheme {}
}