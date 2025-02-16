package com.example.fe_funzo.logic.service.impl

import android.content.Context
import android.util.Log
import com.example.fe_funzo.data.response.CreateUserResponse
import com.example.fe_funzo.infa.client.room.FunzoDatabase
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.client.room.UserDao
import com.example.fe_funzo.infa.client.room.UserRepository
import com.example.fe_funzo.infa.mapper.UserMapper
import com.example.fe_funzo.logic.service.UserRepoService
import com.funzo.funzoProxy.domain.user.UserType
import kotlinx.coroutines.runBlocking

class UserRepoServiceImpl (
    val context: Context,
    private val db: FunzoDatabase =
        FunzoDatabase.getInstance(context = context, dao = UserDao::class.java),
    private val userDao: UserDao = db.userDao(),
    private val userRepo : UserRepository= UserRepository(userDao),

    ): UserRepoService {
    companion object {
        private const val TAG = "UserRepoServiceImpl"
    }

    override fun save(userType: UserType,
                      email: String,
                      response: CreateUserResponse) {
        runBlocking {
            val user: User = UserMapper.mapCreateUserResponse(createUserResponse = response)
            val persistUserResponse = userRepo.insertUser(user)
            Log.i(TAG, "persistUserResponse: ${persistUserResponse}")
        }
    }

    override fun delete(user: User) {
        return runBlocking {
            userRepo.deleteUser(user)
        }
    }

    override fun getFirstUser(): User {
        return runBlocking {
            userRepo.getFirstUser()
        }
    }
}
