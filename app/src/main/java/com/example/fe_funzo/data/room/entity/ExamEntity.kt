package com.example.fe_funzo.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exams")
data class ExamEntity(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "examCode") val examCode: String? = null,
    @ColumnInfo(name = "subject") val subject: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
)
