package com.example.fe_funzo.logic.view_model

import android.content.Context
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.mapper.ExamMapper
import com.example.fe_funzo.logic.service.impl.ExamClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import kotlinx.coroutines.runBlocking

class ExamListViewModel {

    fun getExamList(
        context: Context
    ): List<Exam> {
        val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context = context)
        val user: User = userRepoServiceImpl.getFirstUser()
        val examClient: ExamClientServiceImpl = ExamClientServiceImpl()

        return runBlocking {
            ExamMapper.mapExamListResponseToExamList(examClient.getExamListByTeachersUserCode(userCode = user.userCode))
        }
    }
}
