package com.example.fe_funzo.data.retrofit.response

data class AddOptionResponse(
    val code: String?,
    val questionCode: String?,
    val optionA: String? = null,
    val optionB: String? = null,
    val correctOption: String?,
    val optionC: String? = null,
    val optionD: String? = null
)
