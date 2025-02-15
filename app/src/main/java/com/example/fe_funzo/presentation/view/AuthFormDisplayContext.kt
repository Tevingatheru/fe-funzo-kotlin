package com.example.fe_funzo.presentation.view

import androidx.compose.runtime.Composable
import com.example.fe_funzo.logic.strategy.AuthFormStrategy

class AuthFormDisplayContext<T> {
    private lateinit var authFormStrategy: AuthFormStrategy<T>

    fun setStrategy(authFormStrategy: AuthFormStrategy<T>) {
        this.authFormStrategy = authFormStrategy
    }

    @Composable
    fun Display(policy: T) {
        authFormStrategy.Display(policy)
    }
}
