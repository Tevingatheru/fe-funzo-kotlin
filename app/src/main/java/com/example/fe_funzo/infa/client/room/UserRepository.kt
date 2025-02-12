package com.example.fe_funzo.infa.client.room

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (private val userDao: UserDao) {
    companion object {
        private const val TAG = "UserDaoImpl"
    }

    suspend fun insertUser(user: User) {
        return withContext(Dispatchers.IO) {
            val persistUserResponse = userDao.insertUser(user)
            Log.i(TAG, "persistUserResponse: ${persistUserResponse}")
        }
    }

    fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}