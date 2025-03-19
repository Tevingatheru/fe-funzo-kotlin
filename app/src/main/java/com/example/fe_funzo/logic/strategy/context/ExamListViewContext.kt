package com.example.fe_funzo.logic.strategy.context

import androidx.compose.runtime.Composable
import com.example.fe_funzo.logic.strategy.ExamListViewStrategy

class ExamListViewContext<T> {
    private lateinit var examListViewStrategy: ExamListViewStrategy<T>

    fun setStrategy(dashboardStrategy: ExamListViewStrategy<T>) {
        this.examListViewStrategy = dashboardStrategy
    }

    @Composable
    fun Display(policy: T) {
        examListViewStrategy.Display(policy)
    }
}
