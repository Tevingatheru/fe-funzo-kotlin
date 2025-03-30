package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.retrofit.response.ExamQuestionsResponse
import com.example.fe_funzo.data.retrofit.request.AddQuestionRequest
import com.example.fe_funzo.data.retrofit.request.ModifyQuestionRequest
import com.example.fe_funzo.data.retrofit.response.DeleteQuestionResponse
import com.example.fe_funzo.data.retrofit.response.ModifyQuestionResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.QuestionClient
import com.example.fe_funzo.logic.service.client.QuestionClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class QuestionClientServiceImpl(private val questionClient: QuestionClient = RetrofitClientBuilder.build(QuestionClient::class.java)): QuestionClientService {
    override suspend fun addQuestionToExam(addQuestionRequest : AddQuestionRequest) {
        return withContext(Dispatchers.IO) {
            questionClient.add(addQuestionRequest = addQuestionRequest)
        }
    }

    override suspend fun getQuestionsByExamCode(examCode: String): ExamQuestionsResponse {
        return withContext(Dispatchers.IO) {
            questionClient.getQuestionsByExamCode(examCode = examCode)
        }
    }

    override suspend fun deleteQuestion(code: String): DeleteQuestionResponse {
        return withContext(Dispatchers.IO) {
            questionClient.deleteQuestion(code = code)
        }
    }

    override suspend fun modifyQuestion(modifyQuestionRequest: ModifyQuestionRequest): ModifyQuestionResponse {
        return withContext(Dispatchers.IO) {
            questionClient.modifyQuestion(modifyQuestionRequest = modifyQuestionRequest)
        }
    }
}
