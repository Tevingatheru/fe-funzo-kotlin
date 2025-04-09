package com.example.fe_funzo.logic.view_model

import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.retrofit.response.UserCountResponse
import com.example.fe_funzo.logic.service.client.impl.UserClientServiceImpl
import kotlinx.coroutines.runBlocking

class BIViewModel (): ViewModel() {
    private var totalCount : Int = 0
    private var  adminCount : Int = 0
    private var  teacherCount : Int= 0
    private var  studentCount : Int= 0

    fun getUserStats() {
        runBlocking {
            val response: UserCountResponse = UserClientServiceImpl().getUserCount()
            totalCount = response.totalCount
            adminCount = response.adminCount
            teacherCount = response.teacherCount
            studentCount = response.studentCount
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
}
