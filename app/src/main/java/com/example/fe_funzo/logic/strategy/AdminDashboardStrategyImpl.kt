package com.example.fe_funzo.logic.strategy

import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.policy.AdminDashboardPolicy

class AdminDashboardStrategyImpl : DashboardNavigationStrategy<AdminDashboardPolicy> {

    companion object {
        private const val TAG: String = "AdminDashboardStrategyImpl"
    }

    override fun navigate(policy: AdminDashboardPolicy) {
        NavigationUtil.navigateToAdminDashboard(context = policy.context )
    }
}
