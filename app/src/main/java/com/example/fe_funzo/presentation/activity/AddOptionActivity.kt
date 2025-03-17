package com.example.fe_funzo.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.model.OptionType
import com.example.fe_funzo.data.model.MultipleChoice
import com.example.fe_funzo.data.model.Option
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.data.retrofit.request.EditOptionRequest
import com.example.fe_funzo.data.retrofit.response.OptionResponse
import com.example.fe_funzo.infa.mapper.OptionMapper
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.infa.util.StringUtil
import com.example.fe_funzo.logic.service.client.impl.OptionClientServiceImpl
import com.example.fe_funzo.logic.view_model.AddOptionViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.form.EditTrueFalseForm
import com.example.fe_funzo.presentation.form.TrueFalseOptionForm
import kotlinx.coroutines.runBlocking

private const val TAG: String = "AddOptionActivity"

class AddOptionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val questionDetails: Question = intent.getParcelableExtra(StringUtil.QUESTION_KEY, Question::class.java)!!
        val addOptionViewModel: AddOptionViewModel = AddOptionViewModel()
        val addOptionActivity: AddOptionActivity = this
        addOptionViewModel.setContext(context = addOptionActivity)
        addOptionViewModel.setQuestion(question = questionDetails)

        enableEdgeToEdge()
        runBlocking {
            try {
                showTrueFalseOptionExists(
                    questionDetails = questionDetails,
                    addOptionActivity = addOptionActivity
                )
            } catch (e: Exception) {
                Log.i(TAG, "No option found")
                showIfOptionDoesNotExist(addOptionViewModel)
            }
        }
    }

    private suspend fun showTrueFalseOptionExists(
        questionDetails: Question,
        addOptionActivity: AddOptionActivity,
    ) {
        val  option: Option = getOptionByQuestionCode(questionDetails)

        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        var correctOption: MutableState<Boolean> = remember { mutableStateOf(option.correctOption.toBoolean()) }

                        EditTrueFalseForm(
                            selectedOption = correctOption.value,
                            setSelectedOption = {
                                correctOption.value = it
                            },
                            onEditClick = {
                                sendModifyOptionRequest(
                                    correctOption, questionDetails,
                                    optionCode = option.code!!,
                                    addOptionActivity = addOptionActivity
                                )
                            },
                            onBack = {
                                NavigationUtil.navigateToModifyQuestion(
                                    context = addOptionActivity,
                                    param = mapOf(Pair(StringUtil.QUESTION_KEY, questionDetails))
                                )
                            },
                            onDelete = {
                                val optionClientServiceImpl: OptionClientServiceImpl = OptionClientServiceImpl()
                                runBlocking {
                                    optionClientServiceImpl.deleteByCode(code = option.code!!)
                                    NavigationUtil.navigateToModifyQuestion(
                                        context = addOptionActivity,
                                        param = mapOf(Pair(StringUtil.QUESTION_KEY, questionDetails))
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    private suspend fun AddOptionActivity.getOptionByQuestionCode(
        questionDetails: Question
    ): Option {
        val optionResponse: OptionResponse =
            getOptionResponseByQuestionCode(questionCode = questionDetails.code)
        val option: Option = OptionMapper.mapFromOptionResponse(optionResponse = optionResponse)
        return  option
    }

    private suspend fun getOptionResponseByQuestionCode(questionCode: String): OptionResponse {
        val optionClientServiceImpl = OptionClientServiceImpl()
        val optionResponse: OptionResponse =
            optionClientServiceImpl.getByQuestionCode(questionCode = questionCode)
        return optionResponse
    }

    private fun sendModifyOptionRequest(
        correctOption: MutableState<Boolean>,
        questionDetails: Question,
        optionCode: String,
        addOptionActivity: AddOptionActivity
    ) {
        val optionClientServiceImpl: OptionClientServiceImpl = OptionClientServiceImpl()
        val editAddOptionRequest: EditOptionRequest = EditOptionRequest(
            optionA = null,
            optionB = null,
            optionC = null,
            optionD = null,
            correctOption = correctOption.value.toString(),
            questionCode = questionDetails.code,
            code = optionCode
        )
        runBlocking {
            try {
                optionClientServiceImpl.edit(editOptionRequest = editAddOptionRequest)
                EventAlertUtil.editOptionSuccessful(addOptionActivity = addOptionActivity)
            } catch (e: Exception) {
                Log.e(TAG, "Error: $e")
            }
        }
    }

    private fun showIfOptionDoesNotExist(addOptionViewModel: AddOptionViewModel) {
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                    ) {
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
    val question = addOptionViewModel.getQuestion()

    SelectOption(
        addOptionViewModel = addOptionViewModel,
        setOptionTypeSelected = setOptionTypeSelected,
        optionTypeSelected = optionTypeSelected,
        onOptionSelection = {
            setOptionTypeISSelected(true)
        }
    )

    if (optionTypeIsSelected) {
        if (optionTypeSelected == OptionType.TRUE_FALSE) {
            TrueFalseOptionForm(addOptionViewModel = addOptionViewModel,)
        } else if (optionTypeSelected!! == OptionType.MULTIPLE_CHOICE) {
            MultipleChoiceScreen(question = question, addOptionActivity = addOptionViewModel.getAddOptionActivity())
        }
    }
}

@Composable
fun MultipleChoiceScreen(question: Question, addOptionActivity: AddOptionActivity) {
    val (optionA, setOptionA) = remember { mutableStateOf<String>("") }
    val (optionB, setOptionB) = remember { mutableStateOf<String>("") }
    val (optionC, setOptionC) = remember { mutableStateOf<String>("") }
    val (optionD, setOptionD) = remember { mutableStateOf<String>("") }
    var isOptionASelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
    var isOptionBSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
    var isOptionCSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
    var isOptionDSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }

    val (correctOption, setCorrectOption) = remember { mutableStateOf<String>("") }

    Text("Multiple Choice")

    OutlinedTextField(
        value = optionA,
        onValueChange = { setOptionA(it) },
        label = { Text("Option A") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionASelected.value,
        onClick = {
            setCorrectOption(optionA)
            isOptionASelected.value = true
            isOptionBSelected.value = false
            isOptionCSelected.value = false
            isOptionDSelected.value = false
        }
    )

    OutlinedTextField(
        value = optionB,
        onValueChange = { setOptionB(it) },
        label = { Text("Option B") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionBSelected.value,
        onClick = {
            setCorrectOption(optionB)
            isOptionBSelected.value = true
            isOptionASelected.value= false
            isOptionCSelected.value = false
            isOptionDSelected.value = false
        }
    )

    OutlinedTextField(
        value = optionC,
        onValueChange = { setOptionC(it) },
        label = { Text("Option C") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionCSelected.value,
        onClick = {
            setCorrectOption(optionC)
            isOptionCSelected.value = true
            isOptionASelected.value = false
            isOptionBSelected.value = false
            isOptionDSelected.value = false
        }
    )

    OutlinedTextField(
        value = optionD,
        onValueChange = { setOptionD(it) },
        label = { Text("Option D") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionDSelected.value,
        onClick = {
            setCorrectOption(optionD)
            isOptionDSelected.value = true
            isOptionASelected.value = false
            isOptionBSelected.value = false
            isOptionCSelected.value = false
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
        sendAddOptionRequest(
            optionA, optionB, optionC, optionD, question,
            correctOption = correctOption, addOptionActivity = addOptionActivity
        )
    }) {
        Text("Add Multiple Choice Options")
    }
}

private fun sendAddOptionRequest(
    optionA: String,
    optionB: String,
    optionC: String,
    optionD: String,
    question: Question,
    correctOption: String,
    addOptionActivity: AddOptionActivity
) {
    Log.i(TAG, "optionA: $optionA \n optionB: $optionB \n optionC: $optionC \n optionD: $optionD")
    val multipleChoice: MultipleChoice = MultipleChoice(
        optionA = optionA, optionB = optionB, optionC = optionC, optionD = optionD,
        correctOption = correctOption
    )

    val optionClientServiceImpl: OptionClientServiceImpl = OptionClientServiceImpl()
    runBlocking {

        val createAddOptionRequest: AddOptionRequest = AddOptionRequest(
            optionA = multipleChoice.optionA,
            optionB = multipleChoice.optionB,
            optionC = multipleChoice.optionC,
            optionD = multipleChoice.optionD,
            correctOption = multipleChoice.correctOption,
            questionCode = question.code
        )
        optionClientServiceImpl.addOptionRequest(createAddOptionRequest = createAddOptionRequest)
        EventAlertUtil.addOptionSuccessful(addOptionActivity = addOptionActivity)
        NavigationUtil.navigateToModifyQuestion(
            context = addOptionActivity,
            param = mapOf(Pair(StringUtil.QUESTION_KEY, question))
        )
    }
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

    Text(
        text = "Question: ${addOptionViewModel.getQuestion().question}",
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
    }
}
