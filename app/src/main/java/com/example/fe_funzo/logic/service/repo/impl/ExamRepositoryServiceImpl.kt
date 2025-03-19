package com.example.fe_funzo.logic.service.repo.impl

import android.content.Context
import android.util.Log
import com.example.fe_funzo.data.room.entity.ExamEntity
import com.example.fe_funzo.infa.client.room.ExamDao
import com.example.fe_funzo.infa.client.room.FunzoDatabase
import com.example.fe_funzo.infa.client.room.handler.ExamRepositoryHandler
import com.example.fe_funzo.logic.service.repo.ExamRepositoryService
import kotlinx.coroutines.runBlocking

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
            deleteExistingExam()

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

    override fun deleteExistingExam() {
        runBlocking {
            try {
                val existingExamEntity = examRepo.getExistingExam()
                if (existingExamEntity.uid != null) {
                    deleteExam(examEntity = existingExamEntity)
                }
            } catch (e: Exception) {
                Log.i(TAG, "Error deleting existing user. \nError: $e")
            }
        }
    }
}