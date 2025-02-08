package com.example.fe_funzo.infa.client

import com.example.fe_funzo.infa.client.retrofit.request.CreateUserRequest
import com.example.fe_funzo.infa.client.retrofit.response.CreateUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserClient {
    @POST("/users")
    suspend fun createUser(@Body request: CreateUserRequest): Call<CreateUserResponse>
}
