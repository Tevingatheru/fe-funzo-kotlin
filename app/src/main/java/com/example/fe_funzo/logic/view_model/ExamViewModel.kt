package com.example.fe_funzo.logic.view_model

import androidx.lifecycle.ViewModel
import com.example.fe_funzo.infa.client.retrofit.RetrofitClient
import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.logic.service.impl.UserClientServiceImpl
import com.learner.funzo.model.Exam

class ExamViewModel() : ViewModel() {
    fun getExamsByTeacher(): List<Exam> {
        val userClient: UserClient =
            RetrofitClient.createClient(serviceClass = UserClient::class.java)
        val userService: UserClientServiceImpl = UserClientServiceImpl(userClient = userClient)
        userService.

    }
}
