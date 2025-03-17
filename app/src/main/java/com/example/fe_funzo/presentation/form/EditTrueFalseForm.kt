package com.example.fe_funzo.presentation.form

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditTrueFalseForm(
    selectedOption: Boolean,
    setSelectedOption: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onBack:() -> Unit,
    onDelete:() -> Unit,
) {

    Text("Edit the Correct Option.")

    BooleanSelector(
        label = "Select correct option.",
        modifier = Modifier.padding(16.dp),

        selectedOption = selectedOption,
        setSelectedOption = {
            setSelectedOption(it)
        },
        onSubmit = {
            onEditClick()
        }
    )

    Button(onClick = {
        onBack()
    }) {
        Text("Back")
    }

    Button(onClick = {
        onDelete()
    }) {
        Text("Delete")
    }
}
