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
        var option: Option = Option()
        val optionType = nextQuestion.questionType

        Log.i(TAG, "nextQuestion: $nextQuestion")

        if (optionResponse != null) {
            option = OptionMapper.mapFromOptionResponse(optionResponse)
        }

        if (optionType == null) {
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
            when(OptionType.find(optionType)) {
                OptionType.MULTIPLE_CHOICE -> {
                    context.setContent {
                        Fe_funzoTheme {
                            Scaffold(
                                modifier = Modifier.fillMaxSize()
                            ) { innerPadding ->
                                Column(modifier = Modifier.padding(innerPadding)) {
                                    SetMCQ(
                                        option = option,
                                        questionText = questionText
                                    )
                                }
                            }
                        }
                    }
                }
                OptionType.TRUE_FALSE -> {
                    context.setContent {
                        var (isSubmitted, setSubmitted) = remember { mutableStateOf(false) }

                        Fe_funzoTheme {
                            Scaffold(
                                modifier = Modifier.fillMaxSize()
                            ) { innerPadding ->
                                Column(modifier = Modifier.padding(innerPadding)) {
                                    TrueFalseQuizScreen(
                                        question = questionText,
                                        onSubmit = {
                                            setSubmitted(true)
                                        }
                                    )

                                }
                            }
                        }

                        if (isSubmitted) {
                            SubmitAnswer(option = option)
                            setSubmitted(false)
                        }
                    }
                }

                else -> {
                    throw IllegalArgumentException("OptionType type: \"$optionType\" does not exit")
                }
            }
        }
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
            SubmitAnswer(option = option)
            setSubmitted(false)
        }
    }


    @Composable
    private fun SubmitAnswer(option: Option?) {
        assessAnswer(option)
        nextPosition()
        if (this.currentPosition < this.totalNumberOfQuestions){
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

    private fun emptyOption(option: Option?) = option == null

    @Composable
    private fun DisplayResults() {
        val score: Double = correctAnswers.toDouble() / totalNumberOfQuestions.toDouble()
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
        var isOptionASelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionA == option.correctOption) }
        var isOptionBSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionB == option.correctOption) }
        var isOptionCSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionC == option.correctOption) }
        var isOptionDSelected: MutableState<Boolean> = remember { mutableStateOf<Boolean>(optionD == option.correctOption) }
        var (isSubmitted, setSubmitted) = remember { mutableStateOf(false) }
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
            SubmitAnswer(option)
            setSubmitted(false)
        }
    }

    private fun getCurrentPosition(): Int {
        return currentPosition
    }

    fun nextPosition() {
        ++currentPosition
    }

    fun getCurrentQuestionByPosition(): QuestionContentResponse {
        try {
            return this.getQuestions()[this.currentPosition]
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

    fun getTotalNumberOfQuestions() : Int {
        return this.totalNumberOfQuestions
    }
}
