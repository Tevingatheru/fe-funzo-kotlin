package com.example.fe_funzo.infa.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.data.model.Question
import com.example.fe_funzo.presentation.activity.CreateExamActivity
import com.example.fe_funzo.presentation.activity.SubjectDetailsActivity
import com.example.fe_funzo.presentation.activity.TeacherDashboardActivity
import com.example.fe_funzo.presentation.activity.ViewExamsActivity
import com.example.fe_funzo.presentation.view.AdminDashboard
import com.example.fe_funzo.presentation.view.AdminLandingPage
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.presentation.view.Signup
import com.example.fe_funzo.presentation.view.TeacherLandingPage
import com.example.fe_funzo.presentation.view.UserProfile
import com.example.fe_funzo.presentation.view.UserProfileSettings
import com.example.fe_funzo.data.model.UserType
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.activity.AddOptionActivity
import com.example.fe_funzo.presentation.activity.AddQuestionActivity
import com.example.fe_funzo.presentation.activity.ModifyExamActivity
import com.example.fe_funzo.presentation.activity.ModifyQuestionActivity
import com.example.fe_funzo.presentation.activity.StudentDashboardActivity
import com.example.fe_funzo.presentation.activity.StudentLandingPage

object NavigationUtil {

    private const val TAG = "NavigationUtil"
    private lateinit var intent: Intent

    fun navigateToSignUpActivity(context: Context) {
        Log.i(TAG, "navigateToSignUpActivity")
        intent = Intent(context, Signup::class.java)
        context.startActivity(intent)
    }

    fun navigateToLandingPage(context: Context) {
        val userRepository: UserRepoServiceImpl = UserRepoServiceImpl(context = context)
        navigateToLandingPage(context = context, userType = userRepository.getUserType())
    }

    fun navigateToLandingPage(context: Context, userType: UserType) {
        Log.i(TAG, "Navigate to landing page.")
        if (userType.isAdmin()) {
            Log.i(TAG, "Navigate to admin landing page.")
            intent = Intent(context, AdminLandingPage::class.java)
        } else if (userType.isTeacher()) {
            Log.i(TAG, "Navigate to teacher landing page.")
            intent = Intent(context, TeacherLandingPage::class.java)
        } else if (userType.isStudent()) {
            Log.i(TAG, "Navigate to teacher landing page.")
            intent = Intent(context, StudentLandingPage::class.java)
        } else {
            throw IllegalArgumentException("UserType does not exist: $userType")
        }
        context.startActivity(intent)
    }

    fun navigateToAdminLandingPage(context: Context) {
        intent = Intent(context, AdminLandingPage::class.java)
        context.startActivity(intent)
    }

    fun navigateToTeacherLandingPage(context: Context) {
        intent = Intent(context, TeacherLandingPage::class.java)
        context.startActivity(intent)
    }

    fun navigateToSignIn(context: Context) {
        Log.i(TAG, "Navigate to sign in page.")
        intent = Intent(context, SignIn::class.java)
        context.startActivity(intent)
    }

    fun navigateToUserProfile(context: Context) {
        Log.i(TAG, "navigateToUserProfile")
        intent = Intent(context, UserProfile::class.java)
        context.startActivity(intent)
    }

    fun navigateToUserProfileSettings(context: Context) {
        Log.i(TAG, "navigateToUserProfileSettings")
        intent = Intent(context, UserProfileSettings::class.java)
        context.startActivity(intent)
    }

    fun navigateToAdminDashboard(context: Context) {
        Log.i(TAG, "navigateToDashboard")
        intent = Intent(context, AdminDashboard::class.java)
        context.startActivity(intent)
    }

    fun navigateToTeacherDashboard(context: Context) {
        Log.i(TAG, "navigateToDashboard")
        intent = Intent(context, TeacherDashboardActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToCreateExam(context: Context) {
        Log.i(TAG, "navigateToCreateExam")
        intent = Intent(context, CreateExamActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToViewExams(context: Context) {
        Log.i(TAG, "navigateToViewExams")
        intent = Intent(context, ViewExamsActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToSubjectDetails(context: Context) {
        Log.i(TAG, "navigateToSubjectDetails")
        intent = Intent(context, SubjectDetailsActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToModifyExamActivity(context: Context) {
        val exam: Exam = ExamCacheUtil.getCachedExam(context = context)
        navigateToModifyExamActivity(context = context, param = mapOf(Pair("exam", exam)))
    }

    fun navigateToModifyExamActivity(context: Context, param: Map<String, Exam>) {
        intent =  Intent(context, ModifyExamActivity::class.java)

        val examPair: Exam = param[StringUtil.EXAM_KEY]!!
        intent.putExtra(StringUtil.EXAM_KEY, examPair)
        context.startActivity(intent)
    }

    fun navigateToAddQuestionActivity(context: Context) {
        intent = Intent(context, AddQuestionActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToModifyQuestion(context: Context, param: Map<String, Question>) {
        intent = Intent(context, ModifyQuestionActivity::class.java)
        val question: Question = param[StringUtil.QUESTION_KEY]!!
        intent.putExtra(StringUtil.QUESTION_KEY, question)

        context.startActivity(intent)
    }

    fun navigateToAddOptionActivity(context: Context, param: Map<String, Question>) {
        intent = Intent(context, AddOptionActivity::class.java)
        val question: Question = param[StringUtil.QUESTION_KEY]!!
        intent.putExtra(StringUtil.QUESTION_KEY, question)

        context.startActivity(intent)
    }

    fun navigateToStudentDashboard(context: Context) {
        intent = Intent(context, StudentDashboardActivity::class.java)
        context.startActivity(intent)
    }
}
