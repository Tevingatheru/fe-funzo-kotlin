package com.example.fe_funzo.infa.client.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.reflect.KClass

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class FunzoDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

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
