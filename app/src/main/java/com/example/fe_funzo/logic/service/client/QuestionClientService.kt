package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.response.ExamQuestionsResponse
import com.example.fe_funzo.data.retrofit.request.AddQuestionRequest

interface QuestionClientService {
    suspend fun addQuestionToExam(addQuestionRequest: AddQuestionRequest)
    suspend fun getQuestionsByExamCode(examCode: String): ExamQuestionsResponse
}
