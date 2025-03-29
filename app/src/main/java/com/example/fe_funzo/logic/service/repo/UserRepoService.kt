package com.example.fe_funzo.logic.service.repo

import com.example.fe_funzo.data.model.UserType
import com.example.fe_funzo.data.retrofit.response.UserResponse
import com.example.fe_funzo.data.room.entity.User

interface UserRepoService {
    fun save(email: String,
             response: UserResponse
    )
    fun delete(user: User)
    fun getFirstUser(): User
    fun getUserType(): UserType
    fun deleteAll()
}