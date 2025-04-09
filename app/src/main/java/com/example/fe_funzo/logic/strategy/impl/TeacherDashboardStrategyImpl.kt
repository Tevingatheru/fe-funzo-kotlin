package com.example.fe_funzo.logic.strategy.impl

import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.DashboardNavigationStrategy
import com.example.fe_funzo.logic.strategy.policy.DashboardPolicy

class TeacherDashboardStrategyImpl : DashboardNavigationStrategy<DashboardPolicy> {
    override fun navigate(policy: DashboardPolicy) {
        NavigationUtil.navigateToTeacherDashboard(policy.context)
    }
}
