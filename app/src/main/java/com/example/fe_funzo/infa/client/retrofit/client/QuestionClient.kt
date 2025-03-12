package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.retrofit.response.ExamQuestionsResponse
import com.example.fe_funzo.data.retrofit.request.AddQuestionRequest
import com.example.fe_funzo.data.retrofit.response.AddQuestionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuestionClient {
    @POST(value = "/questions/add")
    suspend fun add(@Body addQuestionRequest: AddQuestionRequest): AddQuestionResponse

    @GET(value = "/questions/exam")
    suspend fun getQuestionsByExamCode(@Query(value = "examCode") examCode: String): ExamQuestionsResponse
}
