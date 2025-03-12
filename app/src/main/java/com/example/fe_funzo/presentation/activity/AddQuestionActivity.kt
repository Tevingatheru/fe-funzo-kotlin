package com.example.fe_funzo.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.room.request.AddQuestionRequest
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.ExamClient
import com.example.fe_funzo.infa.client.retrofit.client.QuestionClient
import com.example.fe_funzo.infa.util.ExamCacheUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.client.impl.QuestionClientServiceImpl
import com.example.fe_funzo.logic.view_model.AddQuestionViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import kotlinx.coroutines.runBlocking

class AddQuestionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addQuestionViewModel: AddQuestionViewModel = AddQuestionViewModel()
        val context: Context = this

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AddQuestionsScreen(
                            addQuestionViewModel = addQuestionViewModel,
                            context = context
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddQuestionsScreen(addQuestionViewModel: AddQuestionViewModel, context: Context) {
    OutlinedTextField(
        value = addQuestionViewModel.getQuestion(),
        onValueChange = { addQuestionViewModel.setQuestion(it) },
        label = { Text("Question") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        addQuestionViewModel.validateQuestion(question = addQuestionViewModel.getQuestion())
        addQuestionViewModel.sendQuestionToBackend(question = addQuestionViewModel.getQuestion(), context = context)
    }) { Text("Add") }
    Button(onClick = {
        NavigationUtil.navigateToModifyExamActivity(context = context)
    }) { Text("Cancel") }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    Fe_funzoTheme {
        AddQuestionsScreen(addQuestionViewModel = AddQuestionViewModel(), context = LocalContext.current)
    }
}
