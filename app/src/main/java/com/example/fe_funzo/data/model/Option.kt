package com.example.fe_funzo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option (
    val optionA: String?,
    val optionB: String?,
    val optionC: String?,
    val optionD: String?,
    val correctOption: String?,
    val optionType: OptionType?,
    val code: String?,
) : Parcelable
