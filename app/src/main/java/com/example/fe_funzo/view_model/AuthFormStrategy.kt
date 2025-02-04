package com.example.fe_funzo.view_model

import androidx.compose.runtime.Composable

interface AuthFormStrategy<T> {
    @Composable
    fun Display(request: T)
}
