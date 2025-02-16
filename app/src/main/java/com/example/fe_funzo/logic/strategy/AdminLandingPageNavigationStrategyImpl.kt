package com.example.fe_funzo.logic.strategy

import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.policy.AdminLandingPolicy

class AdminLandingPageNavigationStrategyImpl : LandingPageNavigationStrategy<AdminLandingPolicy> {
    override fun navigate(policy: AdminLandingPolicy) {
        NavigationUtil.navigateToAdminLandingPage(context = policy.context)
    }
}
