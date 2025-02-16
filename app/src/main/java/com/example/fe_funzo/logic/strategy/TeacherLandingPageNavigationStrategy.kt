package com.example.fe_funzo.logic.strategy

import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.policy.LandingPolicy
import com.example.fe_funzo.logic.strategy.policy.TeacherLandingPolicy

class TeacherLandingPageNavigationStrategy : LandingPageNavigationStrategy<TeacherLandingPolicy> {
    override fun navigate(policy: TeacherLandingPolicy) {
        NavigationUtil.navigateToTeacherLandingPage(context = policy.getContext())
    }
}
