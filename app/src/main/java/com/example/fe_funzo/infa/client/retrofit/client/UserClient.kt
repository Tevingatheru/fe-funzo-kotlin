package com.example.fe_funzo.infa.client.retrofit.client

import com.example.fe_funzo.data.retrofit.request.CreateUserRequest
import com.example.fe_funzo.data.retrofit.response.UserResponse
import com.example.fe_funzo.data.retrofit.response.UserCountResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserClient {
    @POST(value = "/users")
    suspend fun createUser(@Body request: CreateUserRequest): UserResponse

    @GET(value = "/users/count")
    suspend fun getUserCount(): UserCountResponse

    @GET(value = "/users/email")
    suspend fun getUserByEmail(@Query(value = "email") email:String): UserResponse
}
