package com.example.fe_funzo.presentation.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.data.retrofit.request.ModifyQuestionRequest
import com.example.fe_funzo.data.retrofit.response.ModifyQuestionResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.QuestionClient
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.ExamCacheUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.service.client.impl.QuestionClientServiceImpl
import com.example.fe_funzo.logic.view_model.ModifyQuestionViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import kotlinx.coroutines.runBlocking

private const val TAG:String = "ModifyQuestionActivity"

class ModifyQuestionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val questionDetails: Question = intent.getParcelableExtra(StringUtil.QUESTION_KEY, Question::class.java)!!
        val context: ModifyQuestionActivity = this
        val modifyQuestionViewModel: ModifyQuestionViewModel = ModifyQuestionViewModel()
        modifyQuestionViewModel.setQuestion(question = questionDetails)
        modifyQuestionViewModel.setContext(modifyQuestionActivity = context)

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ModifyQuestionScreen(modifyQuestionViewModel = modifyQuestionViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ModifyQuestionScreen(modifyQuestionViewModel: ModifyQuestionViewModel) {
    var enteredQuestion by remember { mutableStateOf(value = modifyQuestionViewModel.getQuestionText()) }

    OutlinedTextField(
        value = enteredQuestion,
        onValueChange = {
            enteredQuestion = it
            modifyQuestionViewModel.setQuestionText(question = it)
        },
        label = { Text(text = "Question")},
        modifier = Modifier.fillMaxWidth(),
    )

    Button(onClick = {
        if (validEdit(question= modifyQuestionViewModel.getQuestionText())) {
            editQuestion(modifyQuestionViewModel= modifyQuestionViewModel)
        }
    }) {
        Text("Edit")
    }

    Button(onClick = {
        NavigationUtil.navigateToAddOptionActivity(
            context = modifyQuestionViewModel.getContext(),
            param = mapOf(Pair(StringUtil.QUESTION_KEY, modifyQuestionViewModel.getQuestion()))
        )
    }) {
        Text(text = "Add Option(s)")
    }
    Button(onClick = {
        NavigationUtil.navigateToModifyExamActivity(context = modifyQuestionViewModel.getContext())
    }) {
        Text(text = "Back")
    }
    Button(onClick = {
        deleteQuestionRequest(question = modifyQuestionViewModel.getQuestion(), modifyQuestionActivity = modifyQuestionViewModel.getModifyQuestionActivity())
    }) {
        Text(text = "Delete Question")
    }
}

fun editQuestion(modifyQuestionViewModel: ModifyQuestionViewModel) {
    val question : String = modifyQuestionViewModel.getQuestionText()
    val context: Context = modifyQuestionViewModel.getContext()
    val questionClient = RetrofitClientBuilder.build(serviceClass = QuestionClient::class.java)
    val questionClientServiceImpl: QuestionClientServiceImpl =
        QuestionClientServiceImpl(questionClient = questionClient)
    val exam = ExamCacheUtil.getCachedExam(context=context)
    val modifyQuestionRequest = ModifyQuestionRequest(
        examCode = exam.examCode!!,
        code = modifyQuestionViewModel.getQuestion().code,
        questionText = question,
        image = null,
    )

    runBlocking {
        val modifyQuestionResponse: ModifyQuestionResponse  = questionClientServiceImpl.modifyQuestion(modifyQuestionRequest = modifyQuestionRequest)
        Log.i(TAG, "Response: $modifyQuestionResponse")
        EventAlertUtil.questionModifiedSuccessfully(modifyQuestionActivity = modifyQuestionViewModel.getModifyQuestionActivity())
    }
}

private fun validEdit(question: String): Boolean {
    return question.isNotBlank()
}

private fun deleteQuestionRequest(question: Question, modifyQuestionActivity : ModifyQuestionActivity ) {
    val questionClient = RetrofitClientBuilder.build(serviceClass = QuestionClient::class.java)
    val questionClientServiceImpl: QuestionClientServiceImpl = QuestionClientServiceImpl(
        questionClient = questionClient
    )

    runBlocking {
        questionClientServiceImpl.deleteQuestion(code = question.code)
        EventAlertUtil.deleteQuestionSuccessful(modifyQuestionActivity = modifyQuestionActivity)
        NavigationUtil.navigateToModifyExamActivity(
            context = modifyQuestionActivity
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview73() {
    Fe_funzoTheme {
        ModifyQuestionScreen(modifyQuestionViewModel = ModifyQuestionViewModel())
    }
}