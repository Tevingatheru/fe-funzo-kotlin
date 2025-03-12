package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.room.request.AddQuestionRequest

interface QuestionClientService {
    suspend fun addQuestionToExam(addQuestionRequest: AddQuestionRequest)
}
