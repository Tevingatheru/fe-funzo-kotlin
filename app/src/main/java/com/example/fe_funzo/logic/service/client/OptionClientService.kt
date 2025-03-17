package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.data.retrofit.request.EditOptionRequest
import com.example.fe_funzo.data.retrofit.response.OptionResponse

interface OptionClientService {
    suspend fun addOptionRequest(createAddOptionRequest: AddOptionRequest): OptionResponse
    suspend fun getByQuestionCode(questionCode: String): OptionResponse
    suspend fun edit(editOptionRequest: EditOptionRequest): OptionResponse
    suspend fun deleteByCode(code: String)
}
