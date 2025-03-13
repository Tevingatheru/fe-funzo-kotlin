package com.example.fe_funzo.data.retrofit.request

data class ModifyQuestionRequest(
    val examCode: String,
    val code: String,
    val questionText: String,
    val image: String? = null,
)
