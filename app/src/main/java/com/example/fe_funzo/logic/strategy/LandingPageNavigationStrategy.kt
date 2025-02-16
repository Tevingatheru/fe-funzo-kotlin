package com.example.fe_funzo.logic.strategy

interface LandingPageNavigationStrategy<T> {
    fun navigate(policy: T)
}
