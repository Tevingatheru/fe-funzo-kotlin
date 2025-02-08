package com.example.fe_funzo.infa.client

import com.example.fe_funzo.infa.client.retrofit.request.CreateUserRequest
import com.example.fe_funzo.infa.client.retrofit.response.CreateUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserClientService (private val userClient: UserClient) {
    suspend fun createUser(request: CreateUserRequest): Response<CreateUserResponse> {
        return withContext(Dispatchers.IO) {
            userClient.createUser(request).execute()
        }
    }
}
