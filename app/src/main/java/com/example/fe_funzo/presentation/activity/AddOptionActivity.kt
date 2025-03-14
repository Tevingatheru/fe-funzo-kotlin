package com.example.fe_funzo.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.OptionType
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.view_model.AddOptionViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme

private const val TAG: String = "AddOptionActivity"

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
    val optionTypeList: MutableList<OptionType> = OptionType.entries.toMutableList()
    val (optionTypeSelected, setOptionTypeSelected) = remember { mutableStateOf<OptionType?>(null) }

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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        optionTypeList.forEach {
            CustomOptionsRadio(
                onSelect = { selected: Boolean ->
                    Log.i(TAG, "${it.name} selected: $selected")
                    if (selected) {
                        setOptionTypeSelected(it)
                    }
                },
                optionTypeName = it.name,
                optionTypeSelected = optionTypeSelected == it
            )
        }
    }
    Button(onClick = {
        Log.i(TAG, "Add Option.")
    }) {
        Text("Select")
    }
}

@Composable
private fun CustomOptionsRadio(onSelect: (Boolean) -> Unit, optionTypeName: String, optionTypeSelected: Boolean) {

    RadioButton(
        selected = optionTypeSelected,
        onClick = {
            onSelect(true)
        },
        colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = MaterialTheme.colorScheme.outline
        )
    )

    Text(
        text = optionTypeName,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(start = 16.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun Preview74() {
    Fe_funzoTheme {
        AddOptionScreen(addOptionViewModel = AddOptionViewModel())
    }
}