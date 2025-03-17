package com.example.fe_funzo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    var question: String,
    val image: String?,

    val code: String
    ) : Parcelable {

    }
