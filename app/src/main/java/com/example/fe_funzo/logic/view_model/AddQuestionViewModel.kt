package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.fe_funzo.data.room.request.AddQuestionRequest
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.QuestionClient
import com.example.fe_funzo.infa.util.ExamCacheUtil
import com.example.fe_funzo.logic.service.client.impl.QuestionClientServiceImpl
import kotlinx.coroutines.runBlocking

class AddQuestionViewModel {
    private var question :MutableState<String> = mutableStateOf("")

    fun getQuestion(): String {
        return question.value
    }

    fun setQuestion(questionInput: String) {
        this.question.value = questionInput
    }

    fun validateQuestion(question: String) {
        if(question.isBlank()) {
            throw Exception("Question is empty exception.")
        }
    }

    fun sendQuestionToBackend(question: String, context: Context) {
        val questionClient = RetrofitClientBuilder.build(QuestionClient::class.java)
        val questionClientServiceImpl: QuestionClientServiceImpl = QuestionClientServiceImpl(questionClient = questionClient)

        val addQuestionRequest: AddQuestionRequest = AddQuestionRequest(
            examCode = ExamCacheUtil.getCachedExam(context = context).examCode!!,
            questionText = question,
            image = null
        )
        runBlocking {
            questionClientServiceImpl.addQuestionToExam(addQuestionRequest = addQuestionRequest)
        }
    }
}
