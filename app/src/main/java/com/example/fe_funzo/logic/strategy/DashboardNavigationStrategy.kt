package com.example.fe_funzo.logic.strategy

interface DashboardNavigationStrategy<T> {
    fun navigate(policy: T)
}
