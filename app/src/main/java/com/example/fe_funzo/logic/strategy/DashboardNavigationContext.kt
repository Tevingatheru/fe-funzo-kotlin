package com.example.fe_funzo.logic.strategy

class DashboardNavigationContext<T> {
    private lateinit var dashboardNavigationStrategy: DashboardNavigationStrategy<T>

    fun setStrategy(authFormStrategy: DashboardNavigationStrategy<T>) {
        this.dashboardNavigationStrategy = authFormStrategy
    }

    fun navigate(policy: T) {
        dashboardNavigationStrategy.navigate(policy)
    }
}
