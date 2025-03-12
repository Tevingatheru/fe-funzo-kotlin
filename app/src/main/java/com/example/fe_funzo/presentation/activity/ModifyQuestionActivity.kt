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
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.QuestionClient
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.service.client.impl.QuestionClientServiceImpl
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import kotlinx.coroutines.runBlocking

class ModifyQuestionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val questionDetails: Question = intent.getParcelableExtra(StringUtil.QUESTION_KEY, Question::class.java)!!
        val context: ModifyQuestionActivity = this

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ModifyQuestionScreen(question = questionDetails, context = context)
                    }
                }
            }
        }
    }
}

@Composable
fun ModifyQuestionScreen(question: Question, context: ModifyQuestionActivity) {
    Text(
        text = question.question,
    )
    Button(onClick = {
        NavigationUtil.navigateToAddOptionActivity(context = context)
    }) {
        Text(text = "Add Option(s)")
    }
    Button(onClick = {
        NavigationUtil.navigateToModifyExamActivity(context = context)
    }) {
        Text(text = "Back")
    }
    Button(onClick = {
        deleteQuestionRequest(question, modifyQuestionActivity = context)
    }) {
        Text(text = "Delete Question")
    }
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
        ModifyQuestionScreen(question = Question(
            question = "question?", code = "code",
            image = null,
            optionA = null,
            optionB = null,
            optionC = null,
            optionD = null,
            correctOption = null,
            questionType = null
        ), context = ModifyQuestionActivity())
    }
}