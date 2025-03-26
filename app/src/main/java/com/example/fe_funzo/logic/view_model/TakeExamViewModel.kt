package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.model.Option
import com.example.fe_funzo.data.model.OptionType
import com.example.fe_funzo.data.retrofit.response.ExamContentResponse
import com.example.fe_funzo.data.retrofit.response.QuestionContentResponse
import com.example.fe_funzo.infa.mapper.OptionMapper
import com.example.fe_funzo.presentation.activity.DisplayQuestion
import com.example.fe_funzo.presentation.activity.TakeExamActivity
import com.example.fe_funzo.presentation.form.MCQForm
import com.example.fe_funzo.presentation.view.TrueFalseQuizScreen


class TakeExamViewModel: ViewModel() {
    companion object {
        private const val TAG: String = "TakeExamViewModel"
    }
    private var examQuestions: MutableList<QuestionContentResponse> = mutableListOf()
    private var currentPosition: Int = 0
    private lateinit var examContentResponse: ExamContentResponse
    private lateinit var context: TakeExamActivity

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
            IncompleteQuestionScreen(questionText = questionText, )
        } else {

            when(OptionType.find(optionType)) {
                OptionType.MULTIPLE_CHOICE -> SetMCQ(
                    option = option,
                    questionText = questionText
                )
                OptionType.TRUE_FALSE -> {
                    TrueFalseQuizScreen(
                        question = questionText,
                        onSubmit = {  }
                    )
                }

                else -> {
                    throw IllegalArgumentException("OptionType type: \"$optionType\" does not exit")
                }
            }
        }
    }

    @Composable
    fun IncompleteQuestionScreen(questionText: String, ) {
        Text(questionText)

        var (isSubmitted, setSubmitted) = remember { mutableStateOf(false) }

        Button(onClick = {
            setSubmitted(true)
        }) {
            Text("Next")
            Log.i(TAG, "isSubmitted: $isSubmitted")
        }

        if (isSubmitted) {
            SubmitAnswer()
            setSubmitted(false)
        }
    }


    @Composable
    private fun SubmitAnswer() {
        nextPosition()
        DisplayQuestion()
//        context.TakeExamScreen()
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

        val (correctOption, setCorrectOption) = remember { mutableStateOf<String>(option.correctOption!!) }

        MCQForm(
            optionA = optionA,
            setOptionA = setOptionA,
            isOptionASelected = isOptionASelected,
            setCorrectOption = setCorrectOption,
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
                Log.i(TAG,"Question submitted")
            },
            questionText = questionText
        )
    }

    private fun getCurrentPosition(): Int {
        return currentPosition
    }

    fun nextPosition() {
        ++currentPosition
    }

    fun getCurrentQuestionByPosition(): QuestionContentResponse {
        return this.getQuestions()[this.currentPosition]
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
}
