package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.CreateExamRequest
import com.example.fe_funzo.data.retrofit.response.CreateExamResponse
import com.example.fe_funzo.data.retrofit.response.ExamListResponse

interface ExamClientService {
    suspend fun createExam(createExamRequest : CreateExamRequest): CreateExamResponse
    suspend fun getExamListByTeachersUserCode(userCode: String): ExamListResponse
    suspend fun getValidExams(): ExamListResponse
}
