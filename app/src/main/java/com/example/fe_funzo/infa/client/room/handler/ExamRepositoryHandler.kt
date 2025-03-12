package com.example.fe_funzo.infa.client.room.handler

import com.example.fe_funzo.data.room.entity.ExamEntity
import com.example.fe_funzo.infa.client.room.ExamDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExamRepositoryHandler(
    private val examDao : ExamDao,
    ) {
    suspend fun insertExamEntity(examEntity: ExamEntity) {
        return withContext(Dispatchers.IO) {
            examDao.insertExam(examEntity = examEntity )
        }
    }

    suspend fun getExistingExam(): ExamEntity {
        return withContext(Dispatchers.IO) {
            examDao.getFirstExamEntity()
        }
    }

    suspend fun deleteExamEntity(examEntity :ExamEntity) {
        return withContext(Dispatchers.IO) {
            examDao.deleteExam(examEntity = examEntity)
        }
    }
}
