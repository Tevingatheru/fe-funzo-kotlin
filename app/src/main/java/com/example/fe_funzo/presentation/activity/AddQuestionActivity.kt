package com.example.fe_funzo.presentation.activity

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
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.view_model.AddQuestionViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme

class AddQuestionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addQuestionViewModel: AddQuestionViewModel = AddQuestionViewModel()
        val context: AddQuestionActivity = this

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
fun AddQuestionsScreen(addQuestionViewModel: AddQuestionViewModel, context: AddQuestionActivity) {
    OutlinedTextField(
        value = addQuestionViewModel.getQuestion(),
        onValueChange = { addQuestionViewModel.setQuestion(it) },
        label = { Text("Question") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        addQuestionViewModel.addQuestion(context)
    }) { Text("Add") }
    Button(onClick = {
        NavigationUtil.navigateToModifyExamActivity(context = context)
    }) { Text("Cancel") }
}



@Preview(showBackground = true)
@Composable
fun Preview72() {
    Fe_funzoTheme {
        AddQuestionsScreen(addQuestionViewModel = AddQuestionViewModel(), context = AddQuestionActivity())
    }
}
