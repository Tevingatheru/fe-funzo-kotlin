package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.retrofit.request.AddResultRequest
import com.example.fe_funzo.data.retrofit.response.AddResultResponse
import com.example.fe_funzo.data.retrofit.response.StudentAnalyticsResponse
import com.example.fe_funzo.data.retrofit.response.TeacherAnalyticsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ResultClient {
    @POST(value = "/results/create")
    suspend fun addResult(@Body addResultRequest: AddResultRequest) : AddResultResponse

    @GET(value = "/results/student/stats/")
    suspend fun getStudentAnalytics(@Query(value = "studentCode") studentCode: String): StudentAnalyticsResponse

    @GET(value = "/results/teacher/stats/")
    suspend fun getTeacherAnalytics(@Query(value = "teacherCode") teacherCode: String): TeacherAnalyticsResponse
}
