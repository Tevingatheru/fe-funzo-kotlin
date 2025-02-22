package com.example.fe_funzo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exam(val threshold: Int,
        val questions: ArrayList<Question>) : Parcelable

