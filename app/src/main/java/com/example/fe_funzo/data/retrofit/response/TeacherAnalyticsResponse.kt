package com.example.fe_funzo.data.retrofit.response

data class TeacherAnalyticsResponse(
    val totalPerformanceAverage: Double,
    val examAverages: List <ExamAverageResponse>
)
