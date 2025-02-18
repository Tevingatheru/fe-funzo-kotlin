package com.example.fe_funzo.logic.service

import com.example.fe_funzo.data.response.UserResponse
import com.example.fe_funzo.infa.client.room.User

interface UserRepoService {
    fun save(email: String,
             response: UserResponse)
    fun delete(user: User)
    fun getFirstUser(): User
}