package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.response.CreateUserResponse
import com.example.fe_funzo.infa.client.room.User
import com.funzo.funzoProxy.domain.user.UserType

interface UserRepoService {
    fun save(userType: UserType,
             email: String,
             response: CreateUserResponse
    )
    fun delete(user: User)
    fun getFirstUser(): User
}