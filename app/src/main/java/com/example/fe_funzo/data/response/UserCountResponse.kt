package com.example.fe_funzo.data.response

data class UserCountResponse (
    val totalCount: Int,
    val adminCount : Int,
    val teacherCount : Int,
    val studentCount : Int
)
