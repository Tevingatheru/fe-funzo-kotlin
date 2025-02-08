package com.example.fe_funzo.logic

import com.example.fe_funzo.infa.client.retrofit.request.CreateUserRequest
import com.example.fe_funzo.infa.client.retrofit.response.CreateUserResponse
import retrofit2.Call
import retrofit2.Response

interface UserClientService {
    suspend fun createUser(request: CreateUserRequest): CreateUserResponse
}
