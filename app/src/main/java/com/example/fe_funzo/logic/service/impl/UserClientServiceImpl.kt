package com.example.fe_funzo.logic.service.impl

import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.data.request.CreateUserRequest
import com.example.fe_funzo.data.response.UserResponse
import com.example.fe_funzo.data.response.UserCountResponse
import com.example.fe_funzo.logic.service.UserClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserClientServiceImpl(private val userClient: UserClient) : UserClientService {

    override suspend fun createUser(request: CreateUserRequest): UserResponse {
        return withContext(Dispatchers.IO) {
            userClient.createUser(request)
        }
    }

    override suspend fun getUserCount(): UserCountResponse {
        return withContext(Dispatchers.IO) {
            userClient.getUserCount()
        }
    }

    override suspend fun getUserByEmail(email: String): UserResponse {
        return withContext(Dispatchers.IO) {
            userClient.getUserByEmail(email)
        }
    }
}
