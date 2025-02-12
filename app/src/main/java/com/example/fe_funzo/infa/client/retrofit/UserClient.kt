package com.example.fe_funzo.infa.client.retrofit

import com.example.fe_funzo.data.request.CreateUserRequest
import com.example.fe_funzo.data.response.CreateUserResponse
import com.example.fe_funzo.data.response.UserCountResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserClient {
    @POST("/users")
    suspend fun createUser(@Body request: CreateUserRequest): CreateUserResponse

    @GET("/users/count")
    suspend fun getUserCount(): UserCountResponse

    @GET("/users/email")
    suspend fun getUserByEmail(email:String): CreateUserResponse
}
