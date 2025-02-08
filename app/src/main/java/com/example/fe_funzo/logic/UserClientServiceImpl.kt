package com.example.fe_funzo.logic

import com.example.fe_funzo.infa.client.UserClient
import com.example.fe_funzo.infa.client.retrofit.request.CreateUserRequest
import com.example.fe_funzo.infa.client.retrofit.response.CreateUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserClientServiceImpl(private val userClient: UserClient) : UserClientService {

    override suspend fun createUser(request: CreateUserRequest): CreateUserResponse{
        return withContext(Dispatchers.IO) {
            userClient.createUser(request)
        }
    }
}
