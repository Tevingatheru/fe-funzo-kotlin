package com.example.fe_funzo.data.room.response

import com.example.fe_funzo.data.model.UserType

data class UserResponse (
    val code: String,
    val email: String,
    val userType: String
) {
    fun getUserType(): UserType {
        val userType: UserType = UserType.find(userType)
        return userType
    }
}
