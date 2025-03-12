package com.example.fe_funzo.data.retrofit.request

data class CreateExamRequest (
    val userCode: String,
    val subjectCode: String,
    val examDescription: String,
)
