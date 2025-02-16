package com.example.fe_funzo.logic.service

import android.content.Context
import com.example.fe_funzo.infa.client.room.User
import com.funzo.funzoProxy.domain.user.UserType

interface UserRepoService {
    fun save(userType: UserType,  email : String)
    fun getUserByEmail(email: String) : User
    fun delete(user: User)
    fun getFirstUser(): User
}