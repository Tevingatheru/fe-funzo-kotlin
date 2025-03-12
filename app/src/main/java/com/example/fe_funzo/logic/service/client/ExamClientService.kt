package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.room.request.CreateExamRequest
import com.example.fe_funzo.data.room.response.CreateExamResponse
import com.example.fe_funzo.data.room.response.ExamListResponse

interface ExamClientService {
    suspend fun createExam(createExamRequest : CreateExamRequest): CreateExamResponse
    suspend fun getExamListByTeachersUserCode(userCode: String): ExamListResponse
}
