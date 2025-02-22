package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.request.CreateSubjectRequest
import com.example.fe_funzo.data.response.CreateSubjectResponse

interface SubjectClientService {
    suspend fun createSubject(createSubjectRequest: CreateSubjectRequest): CreateSubjectResponse
}
