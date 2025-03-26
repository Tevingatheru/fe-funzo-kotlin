package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.retrofit.request.CreateExamRequest
import com.example.fe_funzo.data.retrofit.response.CreateExamResponse
import com.example.fe_funzo.data.retrofit.response.ExamContentResponse
import com.example.fe_funzo.data.retrofit.response.ExamListResponse
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

    override suspend fun getValidExams(): ExamListResponse {
        return withContext(Dispatchers.IO) {
            examClient.getAllExams()
        }
    }

    override suspend fun getExamContentByExamCode(examCode: String): ExamContentResponse {
        return withContext(Dispatchers.IO) {
            examClient.getExamContent(code = examCode)
        }
    }
}
