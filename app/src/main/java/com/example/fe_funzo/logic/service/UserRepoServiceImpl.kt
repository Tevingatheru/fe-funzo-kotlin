package com.example.fe_funzo.logic.service

import android.content.Context
import android.util.Log
import com.example.fe_funzo.data.request.CreateUserRequest
import com.example.fe_funzo.data.response.CreateUserResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClient
import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.infa.client.room.FunzoDatabase
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.client.room.UserDao
import com.example.fe_funzo.infa.client.room.UserRepository
import com.funzo.funzoProxy.domain.user.UserType
import kotlinx.coroutines.runBlocking

class UserRepoServiceImpl : UserRepoService {
    companion object {
        const val TAG = "UserRepoServiceImpl"
    }

    override fun save(userType: UserType, context: Context, email: String) {
        val userClient: UserClient = RetrofitClient.createClient(UserClient::class.java)
        val userService: UserClientServiceImpl = UserClientServiceImpl(userClient = userClient)
        val db: FunzoDatabase =
            FunzoDatabase.getInstance(context = context, dao = UserDao::class.java)
        val userDao: UserDao = db.userDao()

        runBlocking {
            val response: CreateUserResponse = userService.createUser(
                request = CreateUserRequest(userType.type!!, email)
            )
            val user: User = mapUserResponse(createUserResponse = response)
            val userRepo = UserRepository(userDao)
            val persistUserResponse = userRepo.insertUser(user)
            Log.i(TAG, "persistUserResponse: ${persistUserResponse}")
        }
    }

    override fun getUserByEmail(email: String): User {
        val userClient: UserClient = RetrofitClient.createClient(UserClient::class.java)
        val userService: UserClientServiceImpl = UserClientServiceImpl(userClient = userClient)
        return runBlocking {
            mapUserResponse(userService.getUserByEmail(email=email))
        }
    }

    private fun mapUserResponse(createUserResponse: CreateUserResponse): User {
        val user: User
        user = User(uid = null,email= createUserResponse.email, userType =createUserResponse.userType , userCode =createUserResponse.code )
        return user
    }
}