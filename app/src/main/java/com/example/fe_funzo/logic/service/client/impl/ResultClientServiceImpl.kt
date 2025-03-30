package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.retrofit.request.AddResultRequest
import com.example.fe_funzo.data.retrofit.response.AddResultResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.ResultClient
import com.example.fe_funzo.logic.service.client.ResultClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResultClientServiceImpl (private val resultClient: ResultClient = RetrofitClientBuilder.build(ResultClient::class.java)): ResultClientService {
    override suspend fun addResult(addResultRequest: AddResultRequest): AddResultResponse {
        return withContext(Dispatchers.IO) {
            resultClient.addResult(addResultRequest = addResultRequest)
        }
    }
}
