package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.data.retrofit.response.AddOptionResponse
import retrofit2.http.Body

interface OptionClientService {
    suspend fun addOptionRequest(@Body createOptionRequest: AddOptionRequest): AddOptionResponse
}
