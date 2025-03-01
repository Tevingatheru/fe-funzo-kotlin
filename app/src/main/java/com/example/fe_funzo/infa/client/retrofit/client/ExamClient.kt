package com.example.fe_funzo.infa.client.retrofit.client


import com.example.fe_funzo.data.room.request.CreateExamRequest
import com.example.fe_funzo.data.room.response.CreateExamResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ExamClient {
    @POST(value = "/exams")
    suspend fun createExam(@Body createExamRequest: CreateExamRequest): CreateExamResponse
}
