package com.example.fe_funzo.data.form

import com.example.fe_funzo.data.model.Subject

data class ExamForm(
    var subject: Subject = Subject(
        name = "",
        category = "",
        description = "",
        id = null,
        code = "",
    ),
    var name: String = ""
)
