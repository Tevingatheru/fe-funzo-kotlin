package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.room.request.CreateExamRequest
import com.example.fe_funzo.data.room.response.CreateExamResponse
import com.example.fe_funzo.data.room.response.ExamListResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.ExamClient
import com.example.fe_funzo.logic.service.client.ExamClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExamClientServiceImpl(private val examClient: ExamClient = RetrofitClientBuilder.build(ExamClient::class.java)) :
    ExamClientService {
    override suspend fun createExam(createExamRequest : CreateExamRequest): CreateExamResponse {
        return withContext(Dispatchers.IO) {
            examClient.createExam(createExamRequest = createExamRequest)
        }
    }

    override suspend fun getExamListByTeachersUserCode(userCode: String): ExamListResponse {
        return withContext(Dispatchers.IO) {
            examClient.getExamListByTeachersUserCode(userCode = userCode)
        }
    }
}
