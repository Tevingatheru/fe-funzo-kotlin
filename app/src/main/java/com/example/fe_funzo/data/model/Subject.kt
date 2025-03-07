package com.example.fe_funzo.data.model

data class Subject (
    val id : Int?,
    val code: String?,
    val name: String,
    val category: String,
    val description: String,
) {
    init{}
}
