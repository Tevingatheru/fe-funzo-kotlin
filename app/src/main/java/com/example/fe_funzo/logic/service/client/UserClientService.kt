package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.CreateUserRequest
import com.example.fe_funzo.data.retrofit.response.UserResponse
import com.example.fe_funzo.data.retrofit.response.UserCountResponse

interface UserClientService {
    suspend fun createUser(request: CreateUserRequest): UserResponse
    suspend fun getUserCount(): UserCountResponse
    suspend fun getUserByEmail(email: String): UserResponse
}
