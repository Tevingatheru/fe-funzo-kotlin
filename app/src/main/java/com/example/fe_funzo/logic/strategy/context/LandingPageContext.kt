package com.example.fe_funzo.logic.strategy.context

import com.example.fe_funzo.logic.strategy.LandingPageNavigationStrategy

class LandingPageContext<T> {
    lateinit var landingPageNavigationStrategy: LandingPageNavigationStrategy<T>

    fun setStrategy(strategy: LandingPageNavigationStrategy<T>) {
        landingPageNavigationStrategy = strategy
    }

    fun execute(policy: T) {
        landingPageNavigationStrategy.navigate(policy = policy)
    }
}
