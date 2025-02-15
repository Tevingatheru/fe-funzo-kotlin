package com.example.fe_funzo.presentation.view

import androidx.compose.runtime.Composable
import com.example.fe_funzo.logic.strategy.AuthFormStrategy

class AuthFormDisplayContext<T> {
    lateinit var authFormStrategy: AuthFormStrategy<T>

    fun setStrategy(authFormStrategy: AuthFormStrategy<T>) {
        this.authFormStrategy = authFormStrategy
    }

    @Composable
    fun Display(paramObject: T) {
        authFormStrategy.Display(paramObject)
    }
}
