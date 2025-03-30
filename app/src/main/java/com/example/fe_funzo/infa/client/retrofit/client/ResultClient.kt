package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.retrofit.request.AddResultRequest
import com.example.fe_funzo.data.retrofit.response.AddResultResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ResultClient {
    @POST(value = "/results/create")
    suspend fun addResult(@Body addResultRequest: AddResultRequest) : AddResultResponse
}