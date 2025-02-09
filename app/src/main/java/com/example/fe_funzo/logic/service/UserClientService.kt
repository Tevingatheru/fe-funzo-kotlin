package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.request.CreateUserRequest
import com.example.fe_funzo.data.response.CreateUserResponse

interface UserClientService {
    suspend fun createUser(request: CreateUserRequest): CreateUserResponse
}
