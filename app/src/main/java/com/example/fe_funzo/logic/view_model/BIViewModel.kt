package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.retrofit.response.AllExamAnalyticsResponse
import com.example.fe_funzo.data.retrofit.response.ExamAverageResponse
import com.example.fe_funzo.data.retrofit.response.GetAllSubjectStatsResponse
import com.example.fe_funzo.data.retrofit.response.UserCountResponse
import com.example.fe_funzo.data.room.entity.User
import com.example.fe_funzo.logic.service.client.impl.ResultClientServiceImpl
import com.example.fe_funzo.logic.service.client.impl.SubjectClientServiceImpl
import com.example.fe_funzo.logic.service.client.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import kotlinx.coroutines.runBlocking

class BIViewModel (): ViewModel() {
    private var totalUserCount: Int = 0
    private var adminCount: Int = 0
    private var teacherCount: Int = 0
    private var studentCount: Int = 0
    private var subjectCount: Int = 0
    private var examCountPerSubjectResponse: MutableList<Pair<String, Int>> = mutableListOf()
    private lateinit var examAverages: MutableList <ExamAverageResponse>
    private lateinit var context: Context

    fun getUserStats() {
        runBlocking {
            val response: UserCountResponse = UserClientServiceImpl().getUserCount()
            totalUserCount = response.totalCount
            adminCount = response.adminCount
            teacherCount = response.teacherCount
            studentCount = response.studentCount
        }
    }

    fun getExamStats() {
        val user: User = UserRepoServiceImpl(context = context).getFirstUser()
        runBlocking {
            val response: AllExamAnalyticsResponse = ResultClientServiceImpl().getAllAnalytics(user.userCode)
            examAverages = response.examAverages.toMutableList()
        }
    }

    fun getSubjectStats() {
        val subjectClientServiceImpl: SubjectClientServiceImpl = SubjectClientServiceImpl()
        val user = UserRepoServiceImpl(context = context).getFirstUser()
        runBlocking {
            val response : GetAllSubjectStatsResponse = subjectClientServiceImpl.getAllSubjectsStats(adminCode = user.userCode)
            subjectCount = response.subjectCount.toInt()
            examCountPerSubjectResponse = mapResponseToExamCount(response)
        }
    }

    private fun mapResponseToExamCount(response: GetAllSubjectStatsResponse) : MutableList<Pair<String, Int>> {
        val mapping : MutableList<Pair<String, Int>> = mutableListOf()
        if (response.examCountPerSubject.isEmpty()) {
            return mapping
        }
        response.examCountPerSubject.forEach {
            mapping.add(Pair(it.subjectName, it.examCount.toInt()))
        }
        return mapping
    }

    fun setContext(context: Context) {
        this.context = context
    }
    fun getTotalUserCount() : Int {
        return totalUserCount
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

    fun getSubjectCount(): Int {
        return this.subjectCount
    }

    fun getExamCountPerSubjectResponse(): MutableList<Pair<String, Int>> {
        return this.examCountPerSubjectResponse
    }
}
