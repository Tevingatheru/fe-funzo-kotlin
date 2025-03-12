package com.example.fe_funzo.data.retrofit.request

data class AddQuestionRequest(
    val examCode: String,
    val questionText: String,
    val image: String? = null,
)
