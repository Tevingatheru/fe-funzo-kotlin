package com.example.fe_funzo.presentation.activity

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.view_model.AddOptionViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme

class AddOptionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val questionDetails: Question = intent.getParcelableExtra(StringUtil.QUESTION_KEY, Question::class.java)!!
        val addOptionViewModel: AddOptionViewModel = AddOptionViewModel()

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AddOptionScreen(addOptionViewModel = addOptionViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun AddOptionScreen(addOptionViewModel: AddOptionViewModel) {
    Text(
        text = "Select an option type.",
    )

    Button(onClick = {
        NavigationUtil.navigateToModifyQuestion(
            context = addOptionViewModel.getContext(),
            param = mapOf(Pair(StringUtil.QUESTION_KEY, addOptionViewModel.getQuestion()))
        )
    }) {
        Text("Back")
    }
}

@Preview(showBackground = true)
@Composable
fun Preview74() {
    Fe_funzoTheme {
        AddOptionScreen(addOptionViewModel = AddOptionViewModel())
    }
}