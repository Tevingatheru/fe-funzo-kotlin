package com.example.fe_funzo.data.retrofit.response

data class ExamContentResponse(
    var examCode: String? = null,
    var totalNumberOfQuestions: Int? = null,
    var questions: MutableList<QuestionContentResponse> = mutableListOf()
)
