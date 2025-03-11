package com.example.fe_funzo.presentation.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.room.response.ExamListResponse
import com.example.fe_funzo.data.room.response.ExamResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.ExamClient
import com.example.fe_funzo.infa.client.room.User
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.service.impl.ExamClientServiceImpl
import com.example.fe_funzo.logic.service.impl.UserRepoServiceImpl
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import kotlinx.coroutines.runBlocking

private const val TAG : String = "ViewExamsActivity"

class ViewExamsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding),){
                        ViewExamsView(
                            name = "View Exams",
                            context= context
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ViewExamsView(name: String,  context: Context) {
    Text(
        text = "Hello $name!",
    )

    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        NavigationUtil.navigateToSubjectDetails(context = context)
    }) {
        Text("Create Subject")
    }

    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        NavigationUtil.navigateToCreateExam(context = context)
    }) {
        Text("Create Exam")
    }

    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
        NavigationUtil.navigateToLandingPage(context = context)
    }) {
        Text("Back")
    }
    Spacer(modifier = Modifier.height(16.dp))

    ExamListView(context = context)
}

@Composable
fun ExamListView(context: Context ) {
    val userRepoServiceImpl: UserRepoServiceImpl = UserRepoServiceImpl(context = context)
    val user: User = userRepoServiceImpl.getFirstUser()
    val examClient: ExamClientServiceImpl = ExamClientServiceImpl()
    val examList: ExamListResponse;
    val response = runBlocking {
        examList = examClient.getExamListByTeachersUserCode(userCode = user.userCode)
        Log.i(TAG, "examList: $examList")


    }
    ExamList(examList = examList, onEditClick= { exam: ExamResponse ->
        Log.i(TAG, "Editing exam: $exam")
    })
}

@Composable
fun ExamList(examList: ExamListResponse, onEditClick: (ExamResponse) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(examList.exams) { exam ->
            ExamItem(exam = exam, onEditClick = onEditClick)
        }
    }
}

@Composable
fun ExamItem(exam: ExamResponse, onEditClick: (ExamResponse) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onEditClick(exam) }),

        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = exam.subject!!,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = {onEditClick(exam)}) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit exam"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview7() {
    Fe_funzoTheme {
        ViewExamsView("Android", LocalContext.current)
    }
}
