package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.request.CreateUserRequest
import com.example.fe_funzo.data.response.UserResponse
import com.example.fe_funzo.data.response.UserCountResponse

interface UserClientService {
    suspend fun createUser(request: CreateUserRequest): UserResponse
    suspend fun getUserCount(): UserCountResponse
    suspend fun getUserByEmail(email: String): UserResponse
}
