package com.example.fe_funzo.infa.mapper

import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.room.response.ExamListResponse

object ExamMapper {
    fun mapExamListResponseToExamList(examList: ExamListResponse): List<Exam> {
        val examListResult: MutableList<Exam> = mutableListOf()
        examList.exams.forEach {
            val exam: Exam = Exam(examCode = it.examCode, subject = it.subject)
            examListResult.add(exam)
        }
        return examListResult
    }

}
