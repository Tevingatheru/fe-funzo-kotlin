package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.data.retrofit.response.AddOptionResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.OptionClient
import com.example.fe_funzo.logic.service.client.OptionClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OptionClientServiceImpl(
    private val optionClient: OptionClient = RetrofitClientBuilder.build(serviceClass = OptionClient::class.java)
) : OptionClientService {

    override suspend fun addOptionRequest(createOptionRequest: AddOptionRequest): AddOptionResponse {
        return withContext(Dispatchers.IO) {
            optionClient.add(createOptionRequest = createOptionRequest)
        }
    }
}
