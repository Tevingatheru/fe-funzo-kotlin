package com.example.fe_funzo.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "userType") val userType: String,
    @ColumnInfo(name = "userCode") val userCode: String,
)
