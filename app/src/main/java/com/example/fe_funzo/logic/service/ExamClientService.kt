package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.room.request.CreateExamRequest
import com.example.fe_funzo.data.room.response.CreateExamResponse

interface ExamClientService {
    suspend fun createExam(createExamRequest : CreateExamRequest): CreateExamResponse
}
