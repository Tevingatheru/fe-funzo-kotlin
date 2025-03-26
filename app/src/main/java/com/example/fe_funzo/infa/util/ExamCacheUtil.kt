package com.example.fe_funzo.infa.util

import android.content.Context
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.room.entity.ExamEntity
import com.example.fe_funzo.infa.mapper.ExamMapper
import com.example.fe_funzo.logic.service.repo.impl.ExamRepositoryServiceImpl

object ExamCacheUtil {

    fun getCachedExam(context: Context): Exam {
        val examEntity: ExamEntity = ExamRepositoryServiceImpl(
            context = context
        ).getExistingExam()
        val exam: Exam = ExamMapper.mapEntityToDataObject(examEntity = examEntity)

        return exam
    }

    fun setCachedExam(exam: Exam, context: Context) {
        val examRepositoryServiceImpl: ExamRepositoryServiceImpl = ExamRepositoryServiceImpl(
            context = context
        )
        val examEntity: ExamEntity = ExamEntity(
            uid = null,
            examCode = exam.examCode,
            subject = exam.subject,
            description = exam.description
        )

        examRepositoryServiceImpl.insertExam(examEntity = examEntity)
    }
}
