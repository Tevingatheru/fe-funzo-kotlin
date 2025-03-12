package com.example.fe_funzo.logic.service.repo

import com.example.fe_funzo.data.room.entity.ExamEntity

interface ExamRepositoryService {
    fun insertExam(examEntity: ExamEntity)

    fun deleteExam(examEntity: ExamEntity)

    fun getExistingExam(): ExamEntity
}