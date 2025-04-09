package com.example.fe_funzo.logic.view_model

import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.dto.ExamAverageDto
import com.example.fe_funzo.data.dto.TeacherStatsDto
import com.example.fe_funzo.data.retrofit.response.ExamAverageResponse
import com.example.fe_funzo.data.retrofit.response.StudentAnalyticsResponse
import com.example.fe_funzo.data.retrofit.response.TeacherAnalyticsResponse
import com.example.fe_funzo.logic.service.client.impl.ResultClientServiceImpl
import com.example.fe_funzo.logic.service.client.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.activity.TeacherDashboardActivity
import kotlinx.coroutines.runBlocking

class TeacherAnalyticsViewModel: ViewModel() {

    private lateinit var context: TeacherDashboardActivity

    fun setTeacherDashboardActivityContext(context: TeacherDashboardActivity) {
        this.context = context
    }

    fun getContext(): TeacherDashboardActivity {
        return this.context
    }

    fun getTeacherStats(): TeacherStatsDto {
        val teacherCode = UserRepoServiceImpl(
            context = this.context,
        ).getFirstUser().userCode
        val resultClient = ResultClientServiceImpl()
        val totalPerformanceAverage: Double
        val examAverages: MutableList <ExamAverageDto>
        runBlocking {
            val response = resultClient.getTeacherAnalytics(teacherCode = teacherCode)
            totalPerformanceAverage = response.totalPerformanceAverage
            examAverages = mapResponseToDto(response)
        }

        return TeacherStatsDto(
            totalPerformanceAverage = totalPerformanceAverage,
            examAverages = examAverages
        )
    }

    private fun mapResponseToDto(teacherAnalyticsResponse: TeacherAnalyticsResponse) :MutableList<ExamAverageDto>{
        val examAverages : MutableList<ExamAverageDto> = mutableListOf()
        teacherAnalyticsResponse.examAverages.forEach {
            examAverages.add(ExamAverageDto(it.examName, it.averageScoreOfTotalAttempts))
        }

        return examAverages
    }
}
