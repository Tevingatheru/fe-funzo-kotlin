package com.example.fe_funzo.infa.client.retrofit

import com.example.fe_funzo.data.request.CreateUserRequest
import com.example.fe_funzo.data.response.UserResponse
import com.example.fe_funzo.data.response.UserCountResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserClient {
    @POST("/users")
    suspend fun createUser(@Body request: CreateUserRequest): UserResponse

    @GET("/users/count")
    suspend fun getUserCount(): UserCountResponse

    @GET("/users/email")
    suspend fun getUserByEmail(@Query(value = "email") email:String): UserResponse
}
