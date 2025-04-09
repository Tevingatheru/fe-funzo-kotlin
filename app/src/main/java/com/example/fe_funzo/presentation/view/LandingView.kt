package com.example.fe_funzo.presentation.view

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fe_funzo.data.room.entity.User
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import com.example.fe_funzo.logic.strategy.impl.AdminDashboardStrategyImpl
import com.example.fe_funzo.logic.strategy.impl.StudentDashboardStrategyImpl
import com.example.fe_funzo.logic.strategy.context.DashboardNavigationContext
import com.example.fe_funzo.logic.strategy.policy.DashboardPolicy

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
        val currentUser: User = UserRepoServiceImpl(context).getFirstUser()
        val dashboardNavigationContext: DashboardNavigationContext<DashboardPolicy> = DashboardNavigationContext<DashboardPolicy>()

        if (currentUser.findUserType().isAdmin()) {
            dashboardNavigationContext.setStrategy(dashboardStrategy = AdminDashboardStrategyImpl())
        } else if (currentUser.findUserType().isStudent()) {
            dashboardNavigationContext.setStrategy(dashboardStrategy = StudentDashboardStrategyImpl())
        } else {
            throw IllegalArgumentException("This user can not navigate to the dashboard page.")
        }

        dashboardNavigationContext.navigate(DashboardPolicy(
            context = context
        ))
    }

    private fun navigateToProfileScreen(context: Context) {
        NavigationUtil.navigateToUserProfile(context = context)
    }
}
