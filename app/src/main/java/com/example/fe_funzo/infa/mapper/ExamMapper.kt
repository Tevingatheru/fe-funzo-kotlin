package com.example.fe_funzo.infa.mapper

import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.data.room.entity.ExamEntity
import com.example.fe_funzo.data.retrofit.response.ExamListResponse

object ExamMapper {
    fun mapExamListResponseToExamList(examList: ExamListResponse): List<Exam> {
        val examListResult: MutableList<Exam> = mutableListOf()
        examList.exams.forEach {
            val exam: Exam = Exam(examCode = it.examCode, subject = it.subject, description = it.description)
            examListResult.add(exam)
        }
        return examListResult
    }

    fun mapEntityToDataObject(examEntity: ExamEntity): Exam {
        return Exam(
            examCode = examEntity.examCode,
            subject = examEntity.subject,
            description = examEntity.description
        )
    }
}
