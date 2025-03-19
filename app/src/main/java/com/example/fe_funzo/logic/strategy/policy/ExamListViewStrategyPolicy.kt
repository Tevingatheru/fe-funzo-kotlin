package com.example.fe_funzo.logic.strategy.policy

import android.content.Context
import com.example.fe_funzo.data.model.Exam

data class ExamListViewStrategyPolicy(
    val examList: List<Exam>,
    val context: Context
) {

}
