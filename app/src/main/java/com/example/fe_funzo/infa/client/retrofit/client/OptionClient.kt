package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.data.retrofit.response.AddOptionResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OptionClient {
    @POST(value = "/options/create")
    suspend fun add(@Body createOptionRequest: AddOptionRequest): AddOptionResponse
}
