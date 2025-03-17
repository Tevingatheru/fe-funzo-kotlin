package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.data.retrofit.request.EditOptionRequest
import com.example.fe_funzo.data.retrofit.response.OptionResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.OptionClient
import com.example.fe_funzo.logic.service.client.OptionClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OptionClientServiceImpl(
    private val optionClient: OptionClient = RetrofitClientBuilder.build(serviceClass = OptionClient::class.java)
) : OptionClientService {

    override suspend fun addOptionRequest(createAddOptionRequest: AddOptionRequest): OptionResponse {
        return withContext(Dispatchers.IO) {
            optionClient.add(createAddOptionRequest = createAddOptionRequest)
        }
    }

    override suspend fun getByQuestionCode(questionCode: String): OptionResponse {
        return withContext(Dispatchers.IO) {
            optionClient.getByQuestionCode(questionCode = questionCode)
        }
    }

    override suspend fun edit(editOptionRequest: EditOptionRequest): OptionResponse {
        return withContext(Dispatchers.IO) {
            optionClient.edit(editAddOptionRequest = editOptionRequest)
        }
    }

    override suspend fun deleteByCode(code: String) {
        return withContext(Dispatchers.IO) {
            optionClient.delete(code = code)
        }
    }
}
