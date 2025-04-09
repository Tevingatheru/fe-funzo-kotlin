package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.retrofit.response.AllExamAnalyticsResponse
import com.example.fe_funzo.data.retrofit.response.ExamAverageResponse
import com.example.fe_funzo.data.retrofit.response.UserCountResponse
import com.example.fe_funzo.data.room.entity.User
import com.example.fe_funzo.logic.service.client.impl.ResultClientServiceImpl
import com.example.fe_funzo.logic.service.client.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import kotlinx.coroutines.runBlocking

class BIViewModel (): ViewModel() {
    private var totalCount : Int = 0
    private var  adminCount : Int = 0
    private var  teacherCount : Int= 0
    private var  studentCount : Int= 0
    private lateinit var examAverages: MutableList <ExamAverageResponse>

    fun getUserStats() {
        runBlocking {
            val response: UserCountResponse = UserClientServiceImpl().getUserCount()
            totalCount = response.totalCount
            adminCount = response.adminCount
            teacherCount = response.teacherCount
            studentCount = response.studentCount
        }
    }

    fun getExamStats(context: Context) {
        val user: User = UserRepoServiceImpl(context = context).getFirstUser()
        runBlocking {
            val response: AllExamAnalyticsResponse = ResultClientServiceImpl().getAllAnalytics(user.userCode)
            examAverages = response.examAverages.toMutableList()
        }
    }

    fun getTotalUserCount() : Int {
        return totalCount
    }

    fun getTotalTeacherCount() : Int {
        return teacherCount
    }

    fun getTotalAdminCount() : Int {
        return adminCount
    }

    fun getTotalStudentCount() : Int {
        return studentCount
    }

    fun getExamAverageResponse() : MutableList <ExamAverageResponse> {
        return examAverages
    }
}
