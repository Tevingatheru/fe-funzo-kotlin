package com.example.fe_funzo.logic.view_model

import android.content.Context
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.model.UserType
import com.example.fe_funzo.data.room.entity.User
import com.example.fe_funzo.infa.mapper.ExamMapper
import com.example.fe_funzo.logic.service.client.impl.ExamClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import kotlinx.coroutines.runBlocking

class ExamListViewModel {

    fun getExamList(
        context: Context
    ): List<Exam> {
        val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context = context)
        val user: User = userRepoServiceImpl.getFirstUser()
        val examClient: ExamClientServiceImpl = ExamClientServiceImpl()

        if (UserType.find(user.userType).isTeacher()) {
            return runBlocking {
                ExamMapper.mapExamListResponseToExamList(examClient.getExamListByTeachersUserCode(userCode = user.userCode))
            }
        } else if(UserType.find(user.userType).isStudent()) {
            return runBlocking {
                ExamMapper.mapExamListResponseToExamList(examList = examClient.getValidExams())
            }
        } else {
            throw IllegalArgumentException("User type can not view exam list: ${user.userType}")
        }
    }
}
