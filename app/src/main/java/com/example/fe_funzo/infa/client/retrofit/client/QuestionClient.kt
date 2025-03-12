package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.room.request.AddQuestionRequest
import com.example.fe_funzo.data.room.response.AddQuestionResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface QuestionClient {
    @POST(value = "/questions/add")
    suspend fun add(@Body addQuestionRequest: AddQuestionRequest): AddQuestionResponse
}
