package com.example.fe_funzo.presentation.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.model.Subject
import com.example.fe_funzo.logic.view_model.SubjectViewModel

private const val TAG: String = "SubjectSelector"


@SuppressLint("UnrememberedMutableState")
@Composable
fun SimpleSubjectSelector(
    subjectList: List<Subject>,
    selectASubject: (Subject, (Boolean) -> Unit, Boolean) -> Unit,
    subjectViewModel: SubjectViewModel,
) {
    var (expanded, setExpanded) = remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = {
                setExpanded(!expanded)
                Log.i(TAG, "Expand: ${expanded}")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Log.i(TAG, "selectedSubjectName.isNotBlank(): ${subjectViewModel.getSelectedSubjectName().isNotBlank()}\nSubjectName: ${subjectViewModel.getSelectedSubjectName()}")
            Text(
                if (expanded) {
                    "Close"
                } else if (subjectViewModel.getSelectedSubjectName().isNotBlank()) {
                    "Subject: ${subjectViewModel.getSelectedSubjectName()}"
                } else {
                    "Select Subject"
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) }
        ) {
            subjectList.forEach { subject ->
                DropdownMenuItem(
                    onClick = {
                        selectASubject(
                            subject,
                            setExpanded,
                            expanded
                        )
                    },
                    text = {
                        Text(text = subject.name)
                    },
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

