package com.example.fe_funzo.infa.client.room

import androidx.room.Dao
import androidx.room.Delete

import androidx.room.Insert
import androidx.room.Query
import com.example.fe_funzo.data.room.entity.ExamEntity


@Dao
interface ExamDao {
    @Insert
    fun insertExam(examEntity: ExamEntity)

    @Delete
    fun deleteExam(examEntity: ExamEntity)

    @Query("SELECT * FROM exams ORDER BY uid LIMIT 1")
    suspend fun getFirstExamEntity(): ExamEntity
}