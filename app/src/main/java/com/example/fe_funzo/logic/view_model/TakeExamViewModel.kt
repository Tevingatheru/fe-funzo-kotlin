package com.example.fe_funzo.logic.view_model

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.model.Option
import com.example.fe_funzo.data.model.OptionType
import com.example.fe_funzo.data.retrofit.response.ExamContentResponse
import com.example.fe_funzo.data.retrofit.response.OptionResponse
import com.example.fe_funzo.data.retrofit.response.QuestionContentResponse
import com.example.fe_funzo.infa.mapper.OptionMapper
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.presentation.activity.TakeExamActivity
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.form.MCQForm
import com.example.fe_funzo.presentation.view.TrueFalseQuizScreen


class TakeExamViewModel: ViewModel() {
    companion object {
        private const val TAG: String = "TakeExamViewModel"
    }
    @SuppressLint("StaticFieldLeak")
    private lateinit var context: TakeExamActivity
    private lateinit var examContentResponse: ExamContentResponse
    private var examQuestions: MutableList<QuestionContentResponse> = mutableListOf()
    private var currentPosition: Int = 0
    private var totalNumberOfQuestions: Int = 0
    private var correctAnswers: Int = 0
    private var selectedOption: String? = null

    @Composable
    fun DisplayQuestion() {
        Log.i(TAG, "DisplayQuestion")

        val nextQuestion: QuestionContentResponse = getCurrentQuestionByPosition()
        val questionText = nextQuestion.text!!
        val optionResponse = nextQuestion.option
        val optioptionTypeNameType = nextQuestion.questionType
        val option: Option = getOption(optionResponse)

        if (optioptionTypeNameType == null) {
            Log.i(TAG, "should display IncompleteQuestionScreen")
            context.setContent {
                Fe_funzoTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            IncompleteQuestionScreen(questionText = questionText, option = option)
                        }
                    }
                }
            }
        } else {
            context.setContent {
                Fe_funzoTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            when(OptionType.find(optionTypeName = optioptionTypeNameType)) {
                                OptionType.MULTIPLE_CHOICE -> {
                                    SetMCQ(option = option, questionText = questionText)
                                }

                                OptionType.TRUE_FALSE -> {
                                    SetTrueFalse(questionText = questionText, option = option)
                                }
                                else -> {
                                    throw IllegalArgumentException("OptionType type: \"$optioptionTypeNameType\" does not exit")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun TakeExamViewModel.SetTrueFalse(
        questionText: String,
        option: Option
    ) {
        var (isSubmitted, setSubmitted) = remember {
            mutableStateOf(
                false
            )
        }
        var (selectedOption, setSelectedOption) = remember {
            mutableStateOf<Boolean?>(
                null
            )
        }
        TrueFalseQuizScreen(
            question = questionText,
            onSubmit = {
                setSubmitted(true)
                setSelectedOption(it)
            }
        )

        if (isSubmitted) {
            SubmitAnswer(
                option = option,
                selectedOption = selectedOption.toString()
            )
            setSubmitted(false)
        } else {

        }
    }

    private fun getOption(optionResponse: OptionResponse?): Option {
        var option: Option = Option()
        if (optionResponse != null) {
            option = OptionMapper.mapFromOptionResponse(optionResponse)
        }
        return option
    }

    @Composable
    fun IncompleteQuestionScreen(questionText: String, option: Option? ) {
        Text(questionText)

        var (isSubmitted, setSubmitted) = remember { mutableStateOf(false) }

        Button(onClick = {
            setSubmitted(true)
        }) {
            Text("Next")
            Log.i(TAG, "isSubmitted: $isSubmitted")
        }

        if (isSubmitted) {
            SubmitAnswer(option = option, null)
            setSubmitted(false)
        }
    }


    @Composable
    private fun SubmitAnswer(option: Option?, selectedOption: String?) {
        setSelectedOptionItem(selectedOption)
        assessAnswer(option)
        nextPosition()

        if (this.getCurrentPosition() < this.getTotalNumberOfQuestions()){
            DisplayQuestion()
        } else {
            context.setContent {
                Fe_funzoTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            DisplayResults()
                        }
                    }
                }
            }
        }
    }

    private fun assessAnswer(option: Option?) {
        if(answerIsCorrect(option)) {
            ++correctAnswers
        }
    }

    private fun answerIsCorrect(option: Option?): Boolean {
        if (emptyOption(option)) {
            return true
        } else if (correctAnswer(option)) {
            return true
        } else {
            return false
        }
    }

    private fun correctAnswer(option: Option?) =
        option?.correctOption == selectedOption

    private fun emptyOption(option: Option?) = option!!.correctOption == null

    @Composable
    private fun DisplayResults() {
        val score: Double = correctAnswers.toDouble() / getTotalNumberOfQuestions().toDouble()
        val result: String = "Result: $score"


        Text(result)
        Button(onClick = {
            NavigationUtil.navigateToLandingPage(context = context)
        }) {
            Text("Done")
        }
    }

    @Composable
    fun SetMCQ(option: Option, questionText: String) {
        val (optionA, setOptionA) = remember { mutableStateOf<String>(option.optionA ?: "") }
        val (optionB, setOptionB) = remember { mutableStateOf<String>(option.optionB ?: "") }
        val (optionC, setOptionC) = remember { mutableStateOf<String>(option.optionC ?: "") }
        val (optionD, setOptionD) = remember { mutableStateOf<String>(option.optionD ?: "") }
        var isOptionASelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
        var isOptionBSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
        var isOptionCSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
        var isOptionDSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(false) }
        var (isSubmitted, setSubmitted) = remember { mutableStateOf<Boolean>(false) }
        val (selectedOption, setSelectedOption) = remember { mutableStateOf<String?>(null) }

        MCQForm(
            optionA = optionA,
            setOptionA = setOptionA,
            isOptionASelected = isOptionASelected,
            selectedOptionItem = setSelectedOption,
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
                Log.i(TAG,"MCQ submitted")
                setSubmitted(true)

            },
            questionText = questionText
        )

        if (isSubmitted) {
            SubmitAnswer(option, selectedOption = selectedOption)

            setSubmitted(false)
        }
    }

    private fun setSelectedOptionItem(selectedOption: String?) {
        this.selectedOption = selectedOption
    }

    private fun getCurrentPosition(): Int {
        return currentPosition
    }

    private fun nextPosition() {
        ++currentPosition
    }

    private fun getCurrentQuestionByPosition(): QuestionContentResponse {
        try {
            return this.getQuestions()[this.getCurrentPosition()]
        } catch (e: IndexOutOfBoundsException) {
            throw RuntimeException("End of exam reached.")
        }
    }

    private fun getQuestions(): List<QuestionContentResponse> {
        return this.examQuestions!!
    }

    fun setExamContentResponse(examContentResponse: ExamContentResponse) {
        this.examContentResponse = examContentResponse
    }

    fun setQuestions(questions: MutableList<QuestionContentResponse>) {
        examQuestions = questions
    }

    fun setTakeExamActivityContext(context: TakeExamActivity) {
        this.context = context
    }

    fun setTotalNumberOfQuestions(totalNumberOfQuestions : Int) {
        this.totalNumberOfQuestions = totalNumberOfQuestions
    }

    private fun getTotalNumberOfQuestions() : Int {
        return this.totalNumberOfQuestions
    }
}
