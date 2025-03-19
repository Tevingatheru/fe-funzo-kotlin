package com.example.fe_funzo.logic.strategy.impl

import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.DashboardNavigationStrategy
import com.example.fe_funzo.logic.strategy.policy.DashboardPolicy

class AdminDashboardStrategyImpl : DashboardNavigationStrategy<DashboardPolicy> {

    companion object {
        private const val TAG: String = "AdminDashboardStrategyImpl"
    }

    override fun navigate(policy: DashboardPolicy) {
        NavigationUtil.navigateToAdminDashboard(context = policy.context )
    }
}
