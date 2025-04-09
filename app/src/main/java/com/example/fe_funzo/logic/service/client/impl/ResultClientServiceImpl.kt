package com.example.fe_funzo.logic.service.client.impl

import android.util.Log
import com.example.fe_funzo.data.retrofit.request.AddResultRequest
import com.example.fe_funzo.data.retrofit.response.AddResultResponse
import com.example.fe_funzo.data.retrofit.response.StudentAnalyticsResponse
import com.example.fe_funzo.data.retrofit.response.TeacherAnalyticsResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.ResultClient
import com.example.fe_funzo.logic.service.client.ResultClientService
import com.google.firebase.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResultClientServiceImpl (private val resultClient: ResultClient = RetrofitClientBuilder.build(ResultClient::class.java)
): ResultClientService {
    companion object {
        private val TAG : String = "ResultClientService"
    }
    override suspend fun addResult(addResultRequest: AddResultRequest): AddResultResponse {
        return withContext(Dispatchers.IO) {
            resultClient.addResult(addResultRequest = addResultRequest)
        }
    }

    override suspend fun getStudentAnalytics(studentCode: String): StudentAnalyticsResponse {
        return try{
            withContext(Dispatchers.IO) {
                resultClient.getStudentAnalytics(studentCode = studentCode)
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            throw e
        }
    }

    override suspend fun getTeacherAnalytics(teacherCode: String): TeacherAnalyticsResponse {
        return withContext(Dispatchers.IO) {
            resultClient.getTeacherAnalytics(teacherCode = teacherCode)
        }
    }
}
