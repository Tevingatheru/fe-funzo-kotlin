package com.example.fe_funzo.data.room.response

data class UserCountResponse (
    val totalCount: Int,
    val adminCount : Int,
    val teacherCount : Int,
    val studentCount : Int
)
