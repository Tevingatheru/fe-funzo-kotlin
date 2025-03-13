package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.presentation.activity.ModifyQuestionActivity

class ModifyQuestionViewModel (): ViewModel() {
    private lateinit var question: Question
    private lateinit var context: ModifyQuestionActivity

    fun setQuestion(question: Question) {
        this.question = question
    }

    fun getQuestion(): Question {
        return this.question
    }

    fun getQuestionText(): String {
        return this.question.question
    }

    fun setContext(modifyQuestionActivity: ModifyQuestionActivity) {
        this.context = modifyQuestionActivity
    }

    fun getContext() : Context {
        return this.context
    }

    fun getModifyQuestionActivity() : ModifyQuestionActivity {
        return this.context
    }

    fun setQuestionText(question: String) {
        this.question.question = question
    }
}
