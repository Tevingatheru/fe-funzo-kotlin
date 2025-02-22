package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.request.CreateSubjectRequest
import com.example.fe_funzo.data.response.CreateSubjectResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SubjectClient {
    @POST(value = "/subjects")
    suspend fun createSubject(@Body createSubjectRequest: CreateSubjectRequest): CreateSubjectResponse
}
