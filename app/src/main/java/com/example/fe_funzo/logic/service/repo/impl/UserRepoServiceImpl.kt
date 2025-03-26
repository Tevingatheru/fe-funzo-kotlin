package com.example.fe_funzo.logic.service.repo.impl

import android.content.Context
import android.util.Log
import com.example.fe_funzo.data.model.UserType
import com.example.fe_funzo.data.retrofit.response.UserResponse
import com.example.fe_funzo.infa.client.room.FunzoDatabase
import com.example.fe_funzo.data.room.entity.User
import com.example.fe_funzo.infa.client.room.UserDao
import com.example.fe_funzo.infa.client.room.handler.UserRepositoryHandler
import com.example.fe_funzo.logic.service.repo.UserRepoService
import kotlinx.coroutines.runBlocking

class UserRepoServiceImpl (
    private val context: Context,
    private val db: FunzoDatabase =
        FunzoDatabase.getInstance(context = context, dao = UserDao::class.java),
    private val userDao: UserDao = db.userDao(),
    private val userRepo: UserRepositoryHandler = UserRepositoryHandler(userDao),):
    UserRepoService {

    companion object {
        private const val TAG: String = "UserRepoServiceImpl"
    }

    override fun save(email: String,
                      response: UserResponse
    ) {
        runBlocking {
            val user: User = User(uid = null,email = response.email,
                userType = response.userType , userCode = response.code)
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

    override fun getUserType(): UserType {
        return runBlocking {
            val user: User = getFirstUser()
            val userType = UserType.find(user.userType)
            userType
        }
    }
}
