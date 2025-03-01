package com.example.fe_funzo.data.room.request

data class CreateExamRequest (
    val userCode: String,
    val subjectCode: String,
    val examDescription: String,
)
