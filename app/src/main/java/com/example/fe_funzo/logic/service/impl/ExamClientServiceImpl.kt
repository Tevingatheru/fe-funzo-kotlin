package com.example.fe_funzo.logic.service.impl

import com.example.fe_funzo.data.room.request.CreateExamRequest
import com.example.fe_funzo.data.room.response.CreateExamResponse
import com.example.fe_funzo.infa.client.retrofit.client.ExamClient
import com.example.fe_funzo.logic.service.ExamClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExamClientServiceImpl(private val examClient: ExamClient) : ExamClientService {
    override suspend fun createExam(createExamRequest : CreateExamRequest): CreateExamResponse {
        return withContext(Dispatchers.IO) {
            examClient.createExam(createExamRequest = createExamRequest)
        }
    }
}
