package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.room.request.CreateSubjectRequest
import com.example.fe_funzo.data.room.response.GetAllSubjectsResponse
import com.example.fe_funzo.data.room.response.SubjectResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SubjectClient {
    @POST(value = "/subjects")
    suspend fun createSubject(@Body createSubjectRequest: CreateSubjectRequest): SubjectResponse

    @GET(value = "/subjects")
    suspend fun getAllSubjects(): GetAllSubjectsResponse
}
