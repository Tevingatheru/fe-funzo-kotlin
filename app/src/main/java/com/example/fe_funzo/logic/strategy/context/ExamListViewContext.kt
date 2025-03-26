package com.example.fe_funzo.logic.strategy.context

import androidx.compose.runtime.Composable
import com.example.fe_funzo.logic.strategy.ExamListViewStrategy

class ExamListViewContext<T> {
    private lateinit var examListViewStrategy: ExamListViewStrategy<T>

    fun setStrategy(examListViewStrategy: ExamListViewStrategy<T>) {
        this.examListViewStrategy = examListViewStrategy
    }

    @Composable
    fun Display(policy: T) {
        examListViewStrategy.Display(policy)
    }
}
