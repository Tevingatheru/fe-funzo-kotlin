//package com.example.fe_funzo.infa.client.room
//
//import android.content.Context
//import androidx.room.Room
//
//class FunzoDatabaseService(private val funzoDatabase: FunzoDatabase) {
//    companion object {
//        @Volatile
//        private var INSTANCE: FunzoDatabase? = null
//
//        fun getInstance(context: Context): FunzoDatabase {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    FunzoDatabase::class.java,
//                    "funzo_database"
//                ).build()
//                    .also { INSTANCE = it }
//            }
//        }
//    }
//}