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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.retrofit.response.ExamQuestionsResponse
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.QuestionClient
import com.example.fe_funzo.infa.mapper.QuestionMapper
import com.example.fe_funzo.infa.util.ExamCacheUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.service.client.impl.QuestionClientServiceImpl
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import kotlinx.coroutines.runBlocking

class ModifyExamActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this
        val exam: Exam = ExamCacheUtil.getCachedExam(context = context)

        val questionList: List<Question> = getAvailableQuestionsByExamCode(examCode = exam.examCode!!)

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ModifyExamForm(
                            exam = exam,
                            context = context,
                        )
                        QuestionListScreen(questionList = questionList, onEditClicked = { question ->
                            NavigationUtil.navigateToModifyQuestion(
                                context = context,
                                param = mapOf(Pair(StringUtil.QUESTION_KEY, question))
                            )
                        })
                    }
                }
            }
        }
    }

    private fun getAvailableQuestionsByExamCode(examCode: String): List<Question> {
        val questionClientServiceImpl: QuestionClientServiceImpl = QuestionClientServiceImpl(
            questionClient = RetrofitClientBuilder.build(QuestionClient::class.java)
        )

        return runBlocking {
            val questionsResponse: ExamQuestionsResponse = questionClientServiceImpl.getQuestionsByExamCode(
                examCode = examCode
            )
            val questionMutableList: MutableList<Question> = QuestionMapper.mapResponseToList(questionsResponse)
            return@runBlocking questionMutableList
        }
    }
}

@Composable
fun QuestionListScreen(questionList: List<Question>, onEditClicked: (question: Question) -> Unit) {
    questionList.forEach {
        Row {
            Text(it.question!!)
            IconButton(onClick = {
                onEditClicked(it)
            }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Question"
                )
            }
        }
        HorizontalDivider(thickness = 2.dp)
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
            .fillMaxWidth(0.4F)
            .padding(top = 16.dp)
        Button(
            onClick = {
                ExamCacheUtil.setCachedExam(exam = exam, context = context)
                NavigationUtil.navigateToAddQuestionActivity(context = context)
            },
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
            Text(
                text = "Back"
            )
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