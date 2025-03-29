package com.example.fe_funzo.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import com.example.fe_funzo.presentation.form.MCQForm
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
                val  option: Option = getOptionByQuestionCode(questionDetails)

                if (option.optionType!!.isTrueFalse()) {
                    showTrueFalseOptionExists(
                        questionDetails = questionDetails,
                        addOptionActivity = addOptionActivity,
                        option = option
                    )
                } else if (option.optionType!!.isMCQ()) {
                    showExistingMCQ(
                        option = option,
                        question = questionDetails,
                        addOptionActivity = addOptionActivity,
                    )
                }
            } catch (e: Exception) {
                Log.i(TAG, "No option found")
                showIfOptionDoesNotExist(addOptionViewModel)
            }
        }
    }

    private fun showExistingMCQ(
        option: Option,
        question: Question,
        addOptionActivity: AddOptionActivity
    ) {
        setContent {
            Fe_funzoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        EditMultipleChoiceScreen(
                            option = option,
                            addOptionActivity = addOptionActivity,
                            question = question,
                        )
                    }
                }
            }
        }
    }

    private fun showTrueFalseOptionExists(
        questionDetails: Question,
        addOptionActivity: AddOptionActivity,
        option: Option
    ) {
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
                                navigateToModifyQuestion(
                                    addOptionActivity = addOptionActivity,
                                    question = questionDetails
                                )
                            },
                            onDelete = {
                                deleteOption(option, addOptionActivity, questionDetails)
                            }
                        )
                    }
                }
            }
        }
    }

    private suspend fun getOptionByQuestionCode(
        questionDetails: Question
    ): Option {
        val optionResponse: OptionResponse =
            getOptionResponseByQuestionCode(questionCode = questionDetails.code)
        val option: Option = OptionMapper.mapFromOptionResponse(optionResponse = optionResponse)
        Log.i(TAG, "optionResponse: $optionResponse\n option: $option")
        return option
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

private fun deleteOption(
    option: Option,
    addOptionActivity: AddOptionActivity,
    questionDetails: Question
) {
    val optionClientServiceImpl: OptionClientServiceImpl = OptionClientServiceImpl()
    runBlocking {
        optionClientServiceImpl.deleteByCode(code = option.code!!)
        navigateToModifyQuestion(
            addOptionActivity = addOptionActivity,
            question = questionDetails
        )
    }
}

@Composable
fun EditMultipleChoiceScreen(option: Option, addOptionActivity: AddOptionActivity, question: Question) {
    Log.i(TAG, "Analyze existing option: $option")
    val (optionA, setOptionA) = remember { mutableStateOf<String>(option.optionA ?: "") }
    val (optionB, setOptionB) = remember { mutableStateOf<String>(option.optionB ?: "") }
    val (optionC, setOptionC) = remember { mutableStateOf<String>(option.optionC ?: "") }
    val (optionD, setOptionD) = remember { mutableStateOf<String>(option.optionD ?: "") }
    var isOptionASelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionA == option.correctOption) }
    var isOptionBSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionB == option.correctOption) }
    var isOptionCSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionC == option.correctOption) }
    var isOptionDSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionD == option.correctOption) }

    val (correctOption, setCorrectOption) = remember { mutableStateOf<String>(option.correctOption!!) }

    MCQForm(
        optionA = optionA,
        setOptionA = setOptionA,
        isOptionASelected = isOptionASelected,
        selectedOptionItem = setCorrectOption,
        isOptionBSelected = isOptionBSelected,
        isOptionCSelected = isOptionCSelected,
        isOptionDSelected = isOptionDSelected,
        optionB = optionB,
        setOptionB = setOptionB,
        optionC = optionC,
        setOptionC = setOptionC,
        optionD = optionD,
        setOptionD = setOptionD,
        submitMCQOptionsForm = {
            sendModifyOptionRequest(
                optionA = optionA,
                optionB = optionB,
                optionC = optionC,
                optionD = optionD,
                correctOption = correctOption,
                questionCode = question.code,
                optionCode = option.code!!
            )

            EventAlertUtil.editOptionSuccessful(addOptionActivity)
            navigateToModifyQuestion(
                addOptionActivity = addOptionActivity,
                question = question
            )
        },
        questionText = question.question
    )

    Button(onClick = {
        deleteOption(
            option = option,
            addOptionActivity = addOptionActivity,
            questionDetails = question
        )
    }) {
        Text("Delete Option")
    }

    Button(onClick = {
        navigateToModifyQuestion(
            addOptionActivity = addOptionActivity,
            question = question
        )
    }) {
        Text("Back")
    }
}

private fun sendModifyOptionRequest(optionA: String, optionB: String, optionC: String, optionD: String, correctOption: String, questionCode: String, optionCode: String) {
    val optionClientServiceImpl: OptionClientServiceImpl =  OptionClientServiceImpl()
    val editOptionRequest: EditOptionRequest = EditOptionRequest(
        optionA = optionA,
        optionB = optionB,
        optionC = optionC,
        optionD = optionD,
        correctOption = correctOption,
        questionCode = questionCode,
        code = optionCode,
    )

    runBlocking {
        optionClientServiceImpl.edit(editOptionRequest = editOptionRequest)
    }
}

@Composable
private fun AddOptionScreen(addOptionViewModel: AddOptionViewModel) {
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
private fun MultipleChoiceScreen(question: Question, addOptionActivity: AddOptionActivity) {
    val (optionA, setOptionA) = remember { mutableStateOf<String>("") }
    val (optionB, setOptionB) = remember { mutableStateOf<String>("") }
    val (optionC, setOptionC) = remember { mutableStateOf<String>("") }
    val (optionD, setOptionD) = remember { mutableStateOf<String>("") }
    var isOptionASelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
    var isOptionBSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
    var isOptionCSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
    var isOptionDSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }

    val (correctOption, setCorrectOption) = remember { mutableStateOf<String>("") }

    MCQForm(
        optionA = optionA,
        setOptionA = setOptionA,
        isOptionASelected = isOptionASelected,
        selectedOptionItem = setCorrectOption,
        isOptionBSelected = isOptionBSelected,
        isOptionCSelected = isOptionCSelected,
        isOptionDSelected = isOptionDSelected,
        optionB = optionB,
        setOptionB = setOptionB,
        optionC = optionC,
        setOptionC = setOptionC,
        optionD = optionD,
        setOptionD = setOptionD,
        submitMCQOptionsForm = {
            sendAddOptionRequest(
                optionA = optionA,
                optionB = optionB,
                optionC = optionC,
                optionD = optionD,
                question = question,
                correctOption = correctOption, addOptionActivity = addOptionActivity
            )
        },
        questionText = question.question
    )
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
        navigateToModifyQuestion(addOptionActivity, question)
        EventAlertUtil.addOptionSuccessful(addOptionActivity = addOptionActivity)
    }
}

private fun navigateToModifyQuestion(
    addOptionActivity: AddOptionActivity,
    question: Question
) {
    NavigationUtil.navigateToModifyQuestion(
        context = addOptionActivity,
        param = mapOf(Pair(StringUtil.QUESTION_KEY, question))
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

    Text(
        text = "Question: ${addOptionViewModel.getQuestion().question}",
    )

    Button(onClick = {
        navigateToModifyQuestion(
            addOptionActivity = addOptionViewModel.getAddOptionActivity(),
            question = addOptionViewModel.getQuestion()
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
