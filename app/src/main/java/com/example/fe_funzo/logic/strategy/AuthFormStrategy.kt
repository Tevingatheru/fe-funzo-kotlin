package com.example.fe_funzo.logic.strategy

import androidx.compose.runtime.Composable

interface AuthFormStrategy<T> {
    @Composable
    fun Display(request: T)
}
