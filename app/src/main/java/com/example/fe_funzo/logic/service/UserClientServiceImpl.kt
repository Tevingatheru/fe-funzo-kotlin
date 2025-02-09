package com.example.fe_funzo.logic.service

import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.data.request.CreateUserRequest
import com.example.fe_funzo.data.response.CreateUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserClientServiceImpl(private val userClient: UserClient) : UserClientService {

    override suspend fun createUser(request: CreateUserRequest): CreateUserResponse {
        return withContext(Dispatchers.IO) {
            userClient.createUser(request)
        }
    }
}
