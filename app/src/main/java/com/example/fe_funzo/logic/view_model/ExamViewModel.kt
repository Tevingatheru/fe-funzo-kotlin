package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.form.ExamForm
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.UserClient
import com.example.fe_funzo.logic.service.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.data.model.Exam

class ExamViewModel (
    var formData: ExamForm,
    private var examDescription: MutableState<String> = mutableStateOf("")
): ViewModel() {

    fun getExamDescription(): String {
        return examDescription.value
    }

    fun setExamDescription(examDescription : String) {
        this.examDescription.value = examDescription
    }

    fun getExamsByTeacher(context: Context): List<Exam> {

        val userClientServiceImpl: UserClientServiceImpl = UserClientServiceImpl()
        val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context = context)
        // TODO : Get all exams created by user (teacher)
        return emptyList()
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
