package com.example.fe_funzo.logic.service.repo.impl

import android.content.Context
import android.util.Log
import com.example.fe_funzo.data.room.entity.ExamEntity
import com.example.fe_funzo.infa.client.room.ExamDao
import com.example.fe_funzo.infa.client.room.FunzoDatabase
import com.example.fe_funzo.infa.client.room.handler.ExamRepositoryHandler
import com.example.fe_funzo.logic.service.repo.ExamRepositoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ExamRepositoryServiceImpl (
    private val context: Context,
    private val db: FunzoDatabase =
        FunzoDatabase.getInstance(context = context, dao = ExamDao::class.java),
    private val examDao: ExamDao = db.examDao(),
    private val examRepo: ExamRepositoryHandler = ExamRepositoryHandler(examDao),
): ExamRepositoryService {
    private val TAG:String = "ExamRepositoryService"

    override fun insertExam(examEntity: ExamEntity) {
        return runBlocking {
            deleteExistingUser()

            examRepo.insertExamEntity(examEntity = examEntity)
        }
    }

    override fun deleteExam(examEntity: ExamEntity) {
        return runBlocking {
            examRepo.deleteExamEntity(examEntity = examEntity)
        }
    }

    override fun getExistingExam(): ExamEntity{
        return runBlocking {
            examRepo.getExistingExam()
        }
    }

    private suspend fun deleteExistingUser() {
        try {
            val existingExamEntity = examRepo.getExistingExam()
            if (existingExamEntity.uid != null) {
                examRepo.deleteExamEntity(examEntity = existingExamEntity)
            }
        } catch (e: Exception) {
            Log.i(TAG, "Error deleting existing user. \nError: $e")
        }
    }
}