package com.example.fe_funzo.infa.client.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (private val userDao: UserDao) {

    companion object {
        private const val TAG = "UserDaoImpl"
    }

    suspend fun insertUser(user: User) {
        return withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    /**
     * Deprecated: This method is not in use.
     * Use deleteAllUsers() instead.
     * Use deleteAllUsers() until this becomes more in need.
     */
    suspend fun deleteUser(user: User) {
        return withContext(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }

    suspend fun getAllUsers() {
        return withContext(Dispatchers.IO) {
            userDao.getAllUsers()
        }
    }

    suspend fun getFirstUser(): User {
        return withContext(Dispatchers.IO) {
            userDao.getFirstUser()
        }
    }
}
