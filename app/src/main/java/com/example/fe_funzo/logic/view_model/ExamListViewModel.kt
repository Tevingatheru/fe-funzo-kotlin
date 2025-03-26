package com.example.fe_funzo.logic.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.room.entity.User
import com.example.fe_funzo.infa.mapper.ExamMapper
import com.example.fe_funzo.logic.service.client.impl.ExamClientServiceImpl
import com.example.fe_funzo.logic.service.repo.impl.UserRepoServiceImpl
import kotlinx.coroutines.runBlocking

class ExamListViewModel(private val context: Context,
                        private val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context = context),
                        val user: User = userRepoServiceImpl.getFirstUser()): ViewModel() {

    fun getExamList(

    ): List<Exam> {

        val examClient: ExamClientServiceImpl = ExamClientServiceImpl()

        if (user.findUserType().isTeacher()) {
            return runBlocking {
                ExamMapper.mapExamListResponseToExamList(examClient.getExamListByTeachersUserCode(userCode = user.userCode))
            }
        } else if (user.findUserType().isStudent()) {
            return runBlocking {
                ExamMapper.mapExamListResponseToExamList(examList = examClient.getValidExams())
            }
        } else {
            throw IllegalArgumentException("User type can not view exam list: ${user.findUserType()}")
        }
    }
}
