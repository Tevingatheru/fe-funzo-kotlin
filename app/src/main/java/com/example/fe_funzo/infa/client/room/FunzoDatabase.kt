package com.example.fe_funzo.infa.client.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fe_funzo.data.room.entity.ExamEntity
import com.example.fe_funzo.data.room.entity.User

@Database(entities = [User::class, ExamEntity::class], version = 1, exportSchema = false)
abstract class FunzoDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun examDao(): ExamDao

    companion object {
        @Volatile
        private var INSTANCE: FunzoDatabase? = null

        fun getInstance(context: Context, dao: Any): FunzoDatabase {
            synchronized(dao) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FunzoDatabase::class.java,
                    "funzo_database"
                ).build()
                    .also { INSTANCE = it }
            }
        }
    }
}
