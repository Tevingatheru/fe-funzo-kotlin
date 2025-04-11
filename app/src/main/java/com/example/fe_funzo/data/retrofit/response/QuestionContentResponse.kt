package com.example.fe_funzo.data.retrofit.response

data class QuestionContentResponse(
    val examCode: String?= null,
    val questionType: String?= null,
    val text: String?= null,
    val code: String?= null,
    val option: OptionResponse? = null){
    init {

    }
}
