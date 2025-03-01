package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.room.request.CreateUserRequest
import com.example.fe_funzo.data.room.response.UserResponse
import com.example.fe_funzo.data.room.response.UserCountResponse

interface UserClientService {
    suspend fun createUser(request: CreateUserRequest): UserResponse
    suspend fun getUserCount(): UserCountResponse
    suspend fun getUserByEmail(email: String): UserResponse
}
