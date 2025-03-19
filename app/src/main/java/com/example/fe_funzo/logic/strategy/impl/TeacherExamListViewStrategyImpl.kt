package com.example.fe_funzo.logic.strategy.impl

import androidx.compose.runtime.Composable
import com.example.fe_funzo.logic.strategy.ExamListViewStrategy
import com.example.fe_funzo.logic.strategy.policy.ExamListViewStrategyPolicy
import com.example.fe_funzo.presentation.view.TeacherExamViewScreen

class TeacherExamListViewStrategyImpl : ExamListViewStrategy<ExamListViewStrategyPolicy> {
    @Composable
    override fun Display(policy: ExamListViewStrategyPolicy) {
        val teacherExamViewScreen: TeacherExamViewScreen = TeacherExamViewScreen()

        teacherExamViewScreen.ExamListView(examList = policy.examList, context = policy.context)
    }
}