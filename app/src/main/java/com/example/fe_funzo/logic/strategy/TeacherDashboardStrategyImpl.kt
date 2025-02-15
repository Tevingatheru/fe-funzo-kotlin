package com.example.fe_funzo.logic.strategy

import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.policy.TeacherDashboardPolicy

class TeacherDashboardStrategyImpl : DashboardNavigationStrategy<TeacherDashboardPolicy> {
    override fun navigate(policy: TeacherDashboardPolicy) {
        NavigationUtil.navigateToTeacherDashboard(policy.context)
    }
}
