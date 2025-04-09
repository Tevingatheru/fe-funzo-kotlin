package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.AddResultRequest
import com.example.fe_funzo.data.retrofit.response.AddResultResponse
import com.example.fe_funzo.data.retrofit.response.AllExamAnalyticsResponse
import com.example.fe_funzo.data.retrofit.response.StudentAnalyticsResponse
import com.example.fe_funzo.data.retrofit.response.TeacherAnalyticsResponse
import retrofit2.http.Query

interface ResultClientService {
    suspend fun addResult( addResultRequest: AddResultRequest) : AddResultResponse
    suspend fun getStudentAnalytics(studentCode: String): StudentAnalyticsResponse
    suspend fun getTeacherAnalytics(teacherCode: String): TeacherAnalyticsResponse
    suspend fun getAllAnalytics(adminCode: String): AllExamAnalyticsResponse
}