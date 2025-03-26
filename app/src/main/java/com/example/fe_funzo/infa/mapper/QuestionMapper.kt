package com.example.fe_funzo.infa.mapper

import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.data.retrofit.response.ExamQuestionsResponse

object QuestionMapper {
    fun mapResponseToList(questionsResponse: ExamQuestionsResponse): MutableList<Question> {
        val questionMutableList: MutableList<Question> = mutableListOf()

        questionsResponse.questions.forEach {
            val questionItem = Question(
                question = it.question!!,
                code = it.code!!,
                image = null,
            )
            questionMutableList.add(questionItem)
        }
        return questionMutableList
    }
}