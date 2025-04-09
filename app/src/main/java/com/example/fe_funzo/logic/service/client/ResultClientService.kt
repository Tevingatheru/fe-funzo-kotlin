package com.example.fe_funzo.logic.service.client

import com.example.fe_funzo.data.retrofit.request.AddResultRequest
import com.example.fe_funzo.data.retrofit.response.AddResultResponse
import com.example.fe_funzo.data.retrofit.response.StudentAnalyticsResponse

interface ResultClientService {
    suspend fun addResult( addResultRequest: AddResultRequest) : AddResultResponse
    suspend fun getStudentAnalytics(studentCode: String): StudentAnalyticsResponse
}