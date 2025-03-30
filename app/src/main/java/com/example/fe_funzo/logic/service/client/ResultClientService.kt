package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.AddResultRequest
import com.example.fe_funzo.data.retrofit.response.AddResultResponse

interface ResultClientService {
    suspend fun addResult( addResultRequest: AddResultRequest) : AddResultResponse
}