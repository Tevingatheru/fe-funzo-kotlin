package com.example.fe_funzo.logic.view_model

import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.dto.ExamAverageDto
import com.example.fe_funzo.data.dto.StudentAnalyticsDto
import com.example.fe_funzo.data.retrofit.response.StudentAnalyticsResponse
import com.example.fe_funzo.logic.service.client.impl.ResultClientServiceImpl
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.activity.StudentDashboardActivity
import kotlinx.coroutines.runBlocking

class StudentAnalyticsViewModel: ViewModel() {
    private lateinit var context: StudentDashboardActivity

    fun getStudentAnalytics(): StudentAnalyticsDto {
        val studentCode: String = UserRepoServiceImpl(
            context = context
        )!!.getFirstUser()!!.userCode!!
        val resultClientServiceImpl : ResultClientServiceImpl = ResultClientServiceImpl()
        var totalAverageScore : Double = 0.00
        var examAverages : MutableList<ExamAverageDto> = mutableListOf()

        runBlocking {
            val studentAnalyticsResponse: StudentAnalyticsResponse = resultClientServiceImpl.getStudentAnalytics(studentCode = studentCode)
            totalAverageScore = studentAnalyticsResponse.overallAverage
            examAverages = mapResponseToDto(studentAnalyticsResponse)
        }

        val studentAnalyticsDto = StudentAnalyticsDto(
            totalAverageScore = totalAverageScore,
            examAverages = examAverages
        )

        return studentAnalyticsDto
    }

    private fun mapResponseToDto(studentAnalyticsResponse: StudentAnalyticsResponse) :MutableList<ExamAverageDto>{
        val examAverages : MutableList<ExamAverageDto> = mutableListOf()
        studentAnalyticsResponse.examAverages.forEach {
            examAverages.add(ExamAverageDto(it.examName, it.averageScoreOfTotalAttempts))
        }

        return examAverages
    }


    fun setContext(context: StudentDashboardActivity) {
        this.context = context
    }

    fun getContext() : StudentDashboardActivity {
        return this.context
    }
}
