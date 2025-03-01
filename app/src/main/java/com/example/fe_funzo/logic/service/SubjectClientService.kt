package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.room.request.CreateSubjectRequest
import com.example.fe_funzo.data.room.response.GetAllSubjectsResponse
import com.example.fe_funzo.data.room.response.SubjectResponse

interface SubjectClientService {
    suspend fun createSubject(createSubjectRequest: CreateSubjectRequest): SubjectResponse
    suspend fun getAllSubjects(): GetAllSubjectsResponse
}
