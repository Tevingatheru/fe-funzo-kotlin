package com.example.fe_funzo.data.model

enum class MCQOption {
    A,B,C,D;

    fun isA(): Boolean {
        return this == A
    }

    fun isB(): Boolean {
        return this == B
    }

    fun isC(): Boolean {
        return this == C
    }

    fun isD(): Boolean {
        return this == D
    }
}