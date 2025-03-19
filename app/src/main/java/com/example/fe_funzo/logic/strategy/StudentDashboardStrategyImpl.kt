package com.example.fe_funzo.logic.strategy

import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.policy.DashboardPolicy

class StudentDashboardStrategyImpl: DashboardNavigationStrategy<DashboardPolicy> {
    override fun navigate(policy: DashboardPolicy) {
        NavigationUtil.navigateToStudentDashboard(context = policy.context);
    }
}
