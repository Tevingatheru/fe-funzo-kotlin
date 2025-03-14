package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.presentation.activity.AddOptionActivity

class AddOptionViewModel(): ViewModel() {
    private lateinit var context: AddOptionActivity
    private lateinit var question: Question

    fun getContext(): Context {
        return this.context
    }

    fun setContext(context: AddOptionActivity) {
        this.context = context
    }

    fun getQuestion(): Question {
        return this.question
    }

    fun setQuestion(question: Question) {
        this.question = question
    }
}
