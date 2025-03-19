package com.example.fe_funzo.logic.strategy

import androidx.compose.runtime.Composable

interface ExamListViewStrategy<T> {
    @Composable
    fun Display(policy: T)
}