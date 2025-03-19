package com.example.fe_funzo.presentation.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.model.Exam
import com.example.fe_funzo.infa.util.NavigationUtil

class TeacherExamViewScreen {
    companion object{
        private const val TAG : String = "TeacherExamViewScreen"
    }

    @Composable
    fun ExamListView(examList: List<Exam>, context: Context) {
        ExamList(
            examList = examList,
            onEditClick = { exam: Exam ->
                Log.i(TAG, "Navigate to modify exam form: $exam")
                NavigationUtil.navigateToModifyExamActivity(
                    context = context,
                    param = mapOf(Pair("exam", exam))
                )
            }
        )
    }

    @Composable
    fun ExamList(examList: List<Exam>, onEditClick: (Exam) -> Unit) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(examList) { exam ->
                ExamItem(exam = exam, onEditClick = onEditClick)
            }
        }
    }

    @Composable
    fun ExamItem(exam: Exam, onEditClick: (Exam) -> Unit) {
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
}
