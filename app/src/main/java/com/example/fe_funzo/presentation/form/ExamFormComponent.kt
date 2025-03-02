package com.example.fe_funzo.presentation.form

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.model.Subject
import com.example.fe_funzo.logic.view_model.ExamViewModel
import com.example.fe_funzo.logic.view_model.SubjectViewModel
import com.example.fe_funzo.presentation.view.SimpleSubjectSelector

const val TAG : String = "ExamFormComponent"

@SuppressLint("UnrememberedMutableState")
@Composable
fun ExamFormComponent(
    onSubmit: () -> Unit,
    examViewModel: ExamViewModel,
    subjectViewModel: SubjectViewModel,
) {

    val subjectList : List<Subject> =  subjectViewModel.getSubjectList()
    var selectedSubjectCode: MutableState<String> = mutableStateOf("")
    var description by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SimpleSubjectSelector(
            subjectList = subjectList,
            onSubjectSelect = { selectedSubject: Subject ->
                println("Selected Subject code is: ${selectedSubject.code!!}")
                subjectViewModel.setSubjectCode(subjectCode = selectedSubject.code!!)
            },
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
//                subjectViewModel.setSubjectCode(subjectCode = selectedSubjectCode.value!!)
                examViewModel.setExamDescription(examDescription = description)
                onSubmit()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {

            Text("Submit")
        }
    }
}
