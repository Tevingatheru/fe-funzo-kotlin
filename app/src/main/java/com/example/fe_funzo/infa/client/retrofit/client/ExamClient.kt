package com.example.fe_funzo.infa.client.retrofit.client


import com.example.fe_funzo.data.retrofit.request.CreateExamRequest
import com.example.fe_funzo.data.retrofit.response.CreateExamResponse
import com.example.fe_funzo.data.retrofit.response.ExamListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ExamClient {
    @POST(value = "/exams")
    suspend fun createExam(@Body createExamRequest: CreateExamRequest): CreateExamResponse

    @GET(value = "/exams/teacher")
    suspend fun getExamListByTeachersUserCode(@Query(value = "userCode") userCode: String): ExamListResponse
}
