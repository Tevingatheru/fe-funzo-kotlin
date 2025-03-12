package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.infa.client.retrofit.client.UserClient
import com.example.fe_funzo.data.retrofit.request.CreateUserRequest
import com.example.fe_funzo.data.retrofit.response.UserResponse
import com.example.fe_funzo.data.retrofit.response.UserCountResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.logic.service.client.UserClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserClientServiceImpl(private val userClient: UserClient = RetrofitClientBuilder.build(serviceClass = UserClient::class.java)) :
    UserClientService {

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
