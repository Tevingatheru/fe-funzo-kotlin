package com.example.fe_funzo.presentation.view

import androidx.compose.runtime.Composable

interface AuthFormStrategy<T> {
    @Composable
    fun Display(request: T)
}
