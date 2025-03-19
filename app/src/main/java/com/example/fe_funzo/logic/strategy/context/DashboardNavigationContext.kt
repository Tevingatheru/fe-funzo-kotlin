package com.example.fe_funzo.logic.strategy.context

import com.example.fe_funzo.logic.strategy.DashboardNavigationStrategy

class DashboardNavigationContext<T> {
    private lateinit var dashboardNavigationStrategy: DashboardNavigationStrategy<T>

    fun setStrategy(dashboardStrategy: DashboardNavigationStrategy<T>) {
        this.dashboardNavigationStrategy = dashboardStrategy
    }

    fun navigate(policy: T) {
        dashboardNavigationStrategy.navigate(policy)
    }
}
