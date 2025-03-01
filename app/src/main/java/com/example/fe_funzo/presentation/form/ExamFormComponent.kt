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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.form.ExamForm
import com.example.fe_funzo.data.model.Subject
import com.example.fe_funzo.logic.view_model.ExamViewModel
import com.example.fe_funzo.logic.view_model.SubjectViewModel
import com.example.fe_funzo.presentation.view.SimpleSubjectSelector

@SuppressLint("UnrememberedMutableState")
@Composable
fun ExamFormComponent(
    onSubmit: (ExamForm) -> Unit,
    examViewModel: ExamViewModel,
    subjectViewModel: SubjectViewModel,
) {

    val subjectList : List<Subject> =  subjectViewModel.getSubjectList()
    var selectedSubjectCode: MutableState<String> = mutableStateOf("")

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SimpleSubjectSelector(
            subjectList = subjectList,
            onSubjectSelect = { selectedSubject: Subject ->
                println("Selected: ${selectedSubject.name}")
                selectedSubjectCode.value = selectedSubject.code!!
            }
        )

        OutlinedTextField(
            value = examViewModel.formData.name,
            onValueChange = { examViewModel.formData = examViewModel.formData.copy(name = it) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onSubmit(examViewModel.formData) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            val selectedSubject = examViewModel.formData.subject

            val enteredExamDescription: String = examViewModel.formData.name

            subjectViewModel.setSubjectCode(subjectCode = selectedSubjectCode.value )
            examViewModel.setExamDescription(examDescription = enteredExamDescription)
            Text("Submit")


        }
    }
}
