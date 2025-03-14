package com.example.fe_funzo.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.example.fe_funzo.data.model.MultipleChoice
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
        addOptionViewModel.setContext(context = this)
        addOptionViewModel.setQuestion(question = questionDetails)

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
    val (optionTypeSelected, setOptionTypeSelected) = remember { mutableStateOf<OptionType?>(null) }
    val (optionTypeIsSelected, setOptionTypeISSelected) = remember { mutableStateOf<Boolean>(false) }

    SelectOption(
        addOptionViewModel = addOptionViewModel,
        setOptionTypeSelected = setOptionTypeSelected,
        optionTypeSelected = optionTypeSelected,
        onOptionSelection = {
            setOptionTypeISSelected(true)
        }
    )

    if(optionTypeIsSelected) {
        if (optionTypeSelected == OptionType.TRUE_FALSE) {
            TrueFalseOptionForm()
        } else if (optionTypeSelected!! == OptionType.MULTIPLE_CHOICE) {
            MultipleChoiceScreen()
        }
    }
}

@Composable
fun MultipleChoiceScreen() {
    val (optionA, setOptionA) = remember { mutableStateOf<String>("") }
    val (optionB, setOptionB) = remember { mutableStateOf<String>("") }
    val (optionC, setOptionC) = remember { mutableStateOf<String>("") }
    val (optionD, setOptionD) = remember { mutableStateOf<String>("") }

    Text("Multiple Choice")

    OutlinedTextField(
        value = optionA,
        onValueChange = { setOptionA(it) },
        label = { Text("Option A") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = optionB,
        onValueChange = { setOptionB(it) },
        label = { Text("Option B") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = optionC,
        onValueChange = { setOptionC(it) },
        label = { Text("Option C") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = optionD,
        onValueChange = { setOptionD(it) },
        label = { Text("Option D") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
        Log.i(TAG, "optionA: $optionA \n optionB: $optionB \n optionC: $optionC \n optionD: $optionD")
        val multipleChoice: MultipleChoice = MultipleChoice(optionA = optionA, optionB = optionB, optionC = optionC, optionD = optionD)
    }) {
        Text("Add Options to Question")
    }
}

@Composable
fun TrueFalseOptionForm() {
    Text("Select the Correct Option")
    BooleanSelectorExample()
}


@Composable
fun BooleanSelector(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    label: String = "Selection",
    modifier: Modifier = Modifier
) {
    var (selectedOption) = remember { mutableStateOf(value) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf(true to "True", false to "False").forEach { (optionValue, displayText) ->
                Row(
                    modifier = Modifier.padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == optionValue,
                        onClick = {
                            selectedOption = optionValue
                            onValueChange(optionValue)
                        }
                    )
                    Text(
                        text = displayText,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun BooleanSelectorExample() {
    var (isSelected )= remember { mutableStateOf(false) }

    BooleanSelector(
        value = isSelected,
        onValueChange = { isSelected = it },
        label = "Select correct option.",
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun SelectOption(
    addOptionViewModel: AddOptionViewModel,
    setOptionTypeSelected: (OptionType?) -> Unit,
    optionTypeSelected: OptionType?,
    onOptionSelection: () -> Unit
) {
    val optionTypeList: MutableList<OptionType> = OptionType.entries.toMutableList()

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
        onOptionSelection()
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