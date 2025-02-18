package com.example.fe_funzo.infa.mapper

import com.example.fe_funzo.data.response.UserResponse
import com.example.fe_funzo.infa.client.room.User

object UserMapper {
    fun mapCreateUserResponse(userResponse: UserResponse): User {
        return User(uid = null,email = userResponse.email,
            userType = userResponse.userType , userCode = userResponse.code )
    }
}
