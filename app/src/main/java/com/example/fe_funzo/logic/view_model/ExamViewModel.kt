package com.example.fe_funzo.logic.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ExamViewModel (
    private var examDescription: MutableState<String> = mutableStateOf("")
): ViewModel() {

    fun getExamDescription(): String {
        return examDescription.value
    }

    fun setExamDescription(examDescription : String) {
        this.examDescription.value = examDescription
    }

    fun submitCreateExamForm(
        subjectViewModel: SubjectViewModel,
        examViewModel: ExamViewModel,
        description: String,
        onSubmit: () -> Unit
    ) {
        subjectViewModel.setSubjectCode(subjectCode = subjectViewModel.getSelectedSubjectCode())
        examViewModel.setExamDescription(examDescription = description)
        onSubmit()
    }
}
