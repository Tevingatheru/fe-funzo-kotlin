package com.example.fe_funzo.infa.mapper

import com.example.fe_funzo.data.response.CreateUserResponse
import com.example.fe_funzo.infa.client.room.User

object UserMapper {
    fun mapCreateUserResponse(createUserResponse: CreateUserResponse): User {
        return User(uid = null,email = createUserResponse.email,
            userType = createUserResponse.userType , userCode = createUserResponse.code )
    }
}
