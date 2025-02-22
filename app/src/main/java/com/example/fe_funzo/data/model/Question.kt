package com.example.fe_funzo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: Int,
    val question: String?,
    val image: String?,
    val optionA: String?,
    val optionB: String?,
    val optionC: String?,
    val optionD: String?,
    val correctOption: String?,
    val questionType: QuestionType

    ) : Parcelable {

    }

