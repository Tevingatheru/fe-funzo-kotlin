package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.CreateSubjectRequest
import com.example.fe_funzo.data.retrofit.response.GetAllSubjectStatsResponse
import com.example.fe_funzo.data.retrofit.response.GetAllSubjectsResponse
import com.example.fe_funzo.data.retrofit.response.SubjectResponse
import retrofit2.http.Query

interface SubjectClientService {
    suspend fun createSubject(createSubjectRequest: CreateSubjectRequest): SubjectResponse
    suspend fun getAllSubjects(): GetAllSubjectsResponse
    suspend fun getAllSubjectsStats(adminCode: String): GetAllSubjectStatsResponse
}
