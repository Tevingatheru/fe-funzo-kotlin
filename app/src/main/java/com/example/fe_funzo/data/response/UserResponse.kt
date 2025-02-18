package com.example.fe_funzo.data.response

import com.funzo.funzoProxy.domain.user.UserType

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
