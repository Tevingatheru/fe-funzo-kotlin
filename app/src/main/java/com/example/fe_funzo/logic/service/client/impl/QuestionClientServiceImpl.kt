package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.room.request.AddQuestionRequest
import com.example.fe_funzo.infa.client.retrofit.client.QuestionClient
import com.example.fe_funzo.logic.service.client.QuestionClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionClientServiceImpl(private val questionClient: QuestionClient): QuestionClientService {
    override suspend fun addQuestionToExam(addQuestionRequest : AddQuestionRequest) {
        return withContext(Dispatchers.IO) {
            questionClient.add(addQuestionRequest = addQuestionRequest)
        }
    }
}