package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.retrofit.request.CreateSubjectRequest
import com.example.fe_funzo.data.retrofit.response.GetAllSubjectStatsResponse
import com.example.fe_funzo.data.retrofit.response.GetAllSubjectsResponse
import com.example.fe_funzo.data.retrofit.response.SubjectResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SubjectClient {
    @POST(value = "/subjects")
    suspend fun createSubject(@Body createSubjectRequest: CreateSubjectRequest): SubjectResponse

    @GET(value = "/subjects")
    suspend fun getAllSubjects(): GetAllSubjectsResponse

    @GET(value = "/subjects/admin/stats/")
    suspend fun getAllSubjectsStats(@Query(value = "adminCode") adminCode: String): GetAllSubjectStatsResponse
}
