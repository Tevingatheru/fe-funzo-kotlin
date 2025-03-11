package com.example.fe_funzo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exam(  val examCode: String? = null,
                  val subject: String? = null,) : Parcelable

