package com.example.fe_funzo.infa.util

import android.widget.Toast
import com.example.fe_funzo.presentation.activity.AddOptionActivity
import com.example.fe_funzo.presentation.activity.AddQuestionActivity
import com.example.fe_funzo.presentation.activity.ModifyQuestionActivity
import com.example.fe_funzo.presentation.activity.SubjectDetailsActivity
import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.presentation.view.Signup

object EventAlertUtil {

    private const val TAG = "EventAlertUtil"

    fun signupIsSuccessful(context: Signup) {
        Toast.makeText(
            context,
            "Authentication success.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun signupIsFailed(context: Signup) {
        Toast.makeText(
            context,
            "Authentication failed.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun authenticationFailure(context: SignIn) {
        Toast.makeText(
            context,
            "Authentication failed.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun signInHasFailed(context: SignIn) {
        Toast.makeText(
            context,
            "Authentication failed.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun createSubjectSuccess(subjectDetailsActivity: SubjectDetailsActivity) {
        Toast.makeText(
            subjectDetailsActivity,
            "Subject Created.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun createSubjectFailed(subjectDetailsActivity: SubjectDetailsActivity) {
        Toast.makeText(
            subjectDetailsActivity,
            "Create Subject Request Failed.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun addedQuestionSuccessfully(context: AddQuestionActivity) {
        Toast.makeText(
            context,
            "Question added successfully.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun deleteQuestionSuccessful(modifyQuestionActivity: ModifyQuestionActivity) {
        Toast.makeText(
            modifyQuestionActivity,
            "Question deleted successfully.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun questionModifiedSuccessfully(modifyQuestionActivity: ModifyQuestionActivity) {
        Toast.makeText(
            modifyQuestionActivity,
            "Question modified successfully.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun editOptionSuccessful(addOptionActivity: AddOptionActivity) {
        Toast.makeText(
            addOptionActivity,
            "Option modified successfully.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    fun addOptionSuccessful(addOptionActivity: AddOptionActivity) {
        Toast.makeText(
            addOptionActivity,
            "Option added successfully.",
            Toast.LENGTH_SHORT,
        ).show()
    }
}
