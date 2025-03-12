package com.example.fe_funzo.infa.client.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.fe_funzo.data.room.entity.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User ORDER BY uid LIMIT 1")
    suspend fun getFirstUser(): User
}
