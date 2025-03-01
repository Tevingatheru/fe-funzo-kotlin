package com.example.fe_funzo.presentation.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.data.room.response.SubjectResponse
import com.example.fe_funzo.infa.util.EventAlertUtil
import com.example.fe_funzo.logic.view_model.SubjectViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.presentation.form.SubjectForm
import com.example.fe_funzo.data.model.Subject
import com.example.fe_funzo.data.model.UserType
import com.example.fe_funzo.data.room.response.UserResponse
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import kotlinx.coroutines.runBlocking

private const val TAG : String = "SubjectDetailsActivity"

class SubjectDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context : SubjectDetailsActivity = this
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        SubjectDetailsScreen(
                            subjectViewModel = SubjectViewModel(),
                            context = context
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectDetailsScreen(subjectViewModel: SubjectViewModel, context: SubjectDetailsActivity) {
    val subjectForm: SubjectForm = SubjectForm()

    subjectForm.SubjectDetailsForm (
         onSubmit = { subject: Subject ->
            Log.i(TAG, "Create subject. \n Subject: $subject")
            runBlocking {
                try {
                    createSubject(
                        subjectViewModel = subjectViewModel,
                        subject = subject,
                        subjectDetailsActivity = context
                    )
                } catch (e: Exception) {
                    EventAlertUtil.createSubjectFailed(
                        subjectDetailsActivity = context
                    )
                }
            }
        }, onBack = {
            val userType: UserType = getUserTypeFromContext(subjectDetailsActivity = context)

            NavigationUtil.navigateToLandingPage(context = context, userType = userType)
        }
    )
}

private fun getUserTypeFromContext(subjectDetailsActivity: Context): UserType {
    val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context = subjectDetailsActivity)
    val user: User = userRepoServiceImpl.getFirstUser()
    val userType: UserType = UserResponse(
        code = user.userCode,
        email = user.email,
        userType = user.userType
    ).getUserType()

    return userType
}

private suspend fun createSubject(
    subjectViewModel: SubjectViewModel,
    subject: Subject,
    subjectDetailsActivity: SubjectDetailsActivity
) {
    val subjectResponse: SubjectResponse =
        subjectViewModel.createSubject(subject = subject)

    EventAlertUtil.createSubjectSuccess(subjectDetailsActivity = subjectDetailsActivity)
    NavigationUtil.navigateToViewExams(context = subjectDetailsActivity)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    val context = SubjectDetailsActivity()
    Fe_funzoTheme {
        SubjectDetailsScreen(SubjectViewModel(), context = context)
    }
}
