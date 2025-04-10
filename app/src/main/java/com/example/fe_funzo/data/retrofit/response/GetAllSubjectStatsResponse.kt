package com.example.fe_funzo.data.retrofit.response

data class GetAllSubjectStatsResponse(
    val subjectCount: Long,
    val examCountPerSubject: List<ExamCountPerSubjectResponse>
)
