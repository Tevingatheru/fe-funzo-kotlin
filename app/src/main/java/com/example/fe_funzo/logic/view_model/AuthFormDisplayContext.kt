package com.example.fe_funzo.logic.view_model

import androidx.compose.runtime.Composable

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
