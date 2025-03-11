package com.example.fe_funzo.presentation.form

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.model.Subject
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.view_model.ExamViewModel
import com.example.fe_funzo.logic.view_model.SubjectViewModel
import com.example.fe_funzo.presentation.view.SimpleSubjectSelector

private const val TAG : String = "ExamFormComponent"


@SuppressLint("UnrememberedMutableState")
@Composable
fun ExamFormComponent(
    onSubmit: () -> Unit,
    examViewModel: ExamViewModel,
    subjectViewModel: SubjectViewModel,
    subjectList: List<Subject>,
    context: Context
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SimpleSubjectSelector(
            subjectList = subjectList,
            selectASubject = {
                    subject: Subject,
                    setExpanded: (Boolean) -> Unit,
                    expanded: Boolean ->

                subjectViewModel.selectASubject(
                    subject,
                    setExpanded,
                    expanded,
                )

                println("Selected Subject code is: ${subject.code!!}")
                subjectViewModel.setSelectedSubjectCode(code = subject.code )
            },
            subjectViewModel = subjectViewModel
        )

        OutlinedTextField(
            value = examViewModel.getExamDescription(),
            onValueChange = { examViewModel.setExamDescription(it) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                examViewModel.submitCreateExamForm(subjectViewModel, examViewModel, examViewModel.getExamDescription(), onSubmit)
                clearFields(examViewModel = examViewModel, subjectViewModel = subjectViewModel)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Submit")
        }

        Button(
            onClick = {
                NavigationUtil.navigateToViewExams(context = context)
                clearFields(examViewModel, subjectViewModel)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Back")
        }
    }
}

private fun clearFields(
    examViewModel: ExamViewModel,
    subjectViewModel: SubjectViewModel
) {
    examViewModel.setExamDescription("")
    subjectViewModel.setSelectedSubjectCode("")
    subjectViewModel.setSubjectName("")
}


