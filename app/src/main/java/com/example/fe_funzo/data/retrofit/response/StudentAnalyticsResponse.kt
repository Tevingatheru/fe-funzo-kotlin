package com.example.fe_funzo.data.retrofit.response

data class StudentAnalyticsResponse(
    val overallAverage: Double,
    val examAverages: List <ExamAverageResponse>
)
