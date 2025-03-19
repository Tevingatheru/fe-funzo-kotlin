package com.example.fe_funzo.presentation.view

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.strategy.AdminDashboardStrategyImpl
import com.example.fe_funzo.logic.strategy.context.DashboardNavigationContext
import com.example.fe_funzo.logic.strategy.policy.AdminDashboardPolicy

class LandingView {
    @Composable
    fun LandingScreen(context: Context, username: String) {
        Text(
            text = "Welcome ${username}!",
        )
        NavigationOptions(
            navigateToViewExamsActivity = {
                navigateToViewExamsActivity(context =  context)
            },
            navigateToDashboardScreen = {
                navigateToDashboardScreen(context =  context)
            },
            navigateToProfileScreen = {
                navigateToProfileScreen(context =  context)
            }
        )
    }

    private fun navigateToViewExamsActivity(context: Context) {
        NavigationUtil.navigateToViewExams(context = context)
    }

    private fun navigateToDashboardScreen(context: Context) {
        val dashboardNavigationContext: DashboardNavigationContext<AdminDashboardPolicy> = DashboardNavigationContext<AdminDashboardPolicy>()
        dashboardNavigationContext.setStrategy(authFormStrategy = AdminDashboardStrategyImpl())
        dashboardNavigationContext.navigate(AdminDashboardPolicy(
            context = context
        ))
    }

    private fun navigateToProfileScreen(context: Context) {
        NavigationUtil.navigateToUserProfile(context = context)
    }
}
