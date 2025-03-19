package com.example.fe_funzo.logic.strategy.impl

import androidx.compose.runtime.Composable
import com.example.fe_funzo.logic.strategy.ExamListViewStrategy
import com.example.fe_funzo.logic.strategy.policy.ExamListViewStrategyPolicy
import com.example.fe_funzo.presentation.view.StudentExamViewScreen

class StudentExamListViewStrategyImpl: ExamListViewStrategy<ExamListViewStrategyPolicy> {
    @Composable
    override fun Display(policy: ExamListViewStrategyPolicy) {
        val studentExamViewScreen: StudentExamViewScreen = StudentExamViewScreen()

        studentExamViewScreen.ExamListView(examList = policy.examList, context = policy.context)
    }
}