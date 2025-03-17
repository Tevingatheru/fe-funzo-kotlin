package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.data.retrofit.request.EditOptionRequest
import com.example.fe_funzo.data.retrofit.response.OptionResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface OptionClient {
    @POST(value = "/options/create")
    suspend fun add(@Body createAddOptionRequest: AddOptionRequest): OptionResponse

    @GET(value = "/options/question/code")
    suspend fun getByQuestionCode(@Query(value = "questionCode") questionCode: String): OptionResponse

    @PUT(value = "/options/edit")
    suspend fun edit(@Body editAddOptionRequest: EditOptionRequest): OptionResponse

    @DELETE(value = "/options/delete")
    suspend fun delete(@Query(value = "code") code: String)
}
