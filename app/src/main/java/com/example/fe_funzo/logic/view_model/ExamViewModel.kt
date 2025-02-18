package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.client.retrofit.RetrofitClient
import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.logic.service.impl.UserClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.learner.funzo.model.Exam

class ExamViewModel : ViewModel() {
    fun getExamsByTeacher(context: Context): List<Exam> {
        val userClient: UserClient =
            RetrofitClient.createClient(serviceClass = UserClient::class.java)
        val userClientServiceImpl: UserClientServiceImpl = UserClientServiceImpl(userClient = userClient)
        val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context = context)
        TODO("Get all exams created by teacher")
        return emptyList()
    }
}
