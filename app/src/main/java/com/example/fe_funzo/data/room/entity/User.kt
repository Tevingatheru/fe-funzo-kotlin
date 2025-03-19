package com.example.fe_funzo.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fe_funzo.data.model.UserType

@Entity
data class User(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "userType") val userType: String,
    @ColumnInfo(name = "userCode") val userCode: String,
) {
    fun findUserType() : UserType {
        return UserType.find(this.userType)
    }
}
