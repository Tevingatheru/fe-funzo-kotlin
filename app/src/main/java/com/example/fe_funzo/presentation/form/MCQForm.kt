package com.example.fe_funzo.presentation.form

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val TAG: String = "MCQForm"

@Composable
fun MCQForm(
    optionA: String,
    setOptionA: (String) -> Unit,
    isOptionASelected: MutableState<Boolean>,
    selectedOptionItem: (String) -> Unit,
    isOptionBSelected: MutableState<Boolean>,
    isOptionCSelected: MutableState<Boolean>,
    isOptionDSelected: MutableState<Boolean>,
    optionB: String,
    setOptionB: (String) -> Unit,
    optionC: String,
    setOptionC: (String) -> Unit,
    optionD: String,
    setOptionD: (String) -> Unit,
    submitMCQOptionsForm: () -> Unit,
    questionText: String
) {
    Text("Multiple Choice")
    Text("Question: $questionText")

    OutlinedTextField(
        value = optionA,
        onValueChange = { setOptionA(it) },
        label = { Text("Option A") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionASelected.value,
        onClick = {
                selectA(
                    setCorrectOption = selectedOptionItem,
                    optionA = optionA,
                    isOptionASelected = isOptionASelected,
                    isOptionBSelected = isOptionBSelected,
                    isOptionCSelected = isOptionCSelected,
                    isOptionDSelected = isOptionDSelected
                )
        }
    )

    OutlinedTextField(
        value = optionB,
        onValueChange = { setOptionB(it) },
        label = { Text("Option B") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionBSelected.value,
        onClick = {
            selectB(
                setCorrectOption = selectedOptionItem,
                optionB = optionB,
                isOptionBSelected = isOptionBSelected,
                isOptionASelected = isOptionASelected,
                isOptionCSelected = isOptionCSelected,
                isOptionDSelected = isOptionDSelected
            )
        }
    )

    OutlinedTextField(
        value = optionC,
        onValueChange = { setOptionC(it) },
        label = { Text("Option C") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionCSelected.value,
        onClick = {
            selectC(
                setCorrectOption = selectedOptionItem,
                optionC = optionC,
                isOptionCSelected = isOptionCSelected,
                isOptionASelected = isOptionASelected,
                isOptionBSelected = isOptionBSelected,
                isOptionDSelected = isOptionDSelected
            )
        }
    )

    OutlinedTextField(
        value = optionD,
        onValueChange = { setOptionD(it) },
        label = { Text("Option D") },
        modifier = Modifier.fillMaxWidth()
    )

    RadioButton(
        selected = isOptionDSelected.value,
        onClick = {
            selectD(
                setCorrectOption = selectedOptionItem,
                optionD = optionD,
                isOptionDSelected = isOptionDSelected,
                isOptionASelected = isOptionASelected,
                isOptionBSelected = isOptionBSelected,
                isOptionCSelected = isOptionCSelected
            )
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
        clearFields(
            isOptionASelected = isOptionASelected,
            isOptionBSelected = isOptionBSelected,
            isOptionCSelected = isOptionCSelected,
            isOptionDSelected = isOptionDSelected
        )
        submitMCQOptionsForm()
    }) {
        Text("Add/Edit Multiple Choice Options")
    }
}

private fun selectA(
    setCorrectOption: (String) -> Unit,
    optionA: String,
    isOptionASelected: MutableState<Boolean>,
    isOptionBSelected: MutableState<Boolean>,
    isOptionCSelected: MutableState<Boolean>,
    isOptionDSelected: MutableState<Boolean>
) {
    setCorrectOption(optionA)
    isOptionASelected.value = true
    setBtoFalse(isOptionBSelected)
    setCtoFalse(isOptionCSelected)
    setDtoFalse(isOptionDSelected)
}

private fun selectB(
    setCorrectOption: (String) -> Unit,
    optionB: String,
    isOptionBSelected: MutableState<Boolean>,
    isOptionASelected: MutableState<Boolean>,
    isOptionCSelected: MutableState<Boolean>,
    isOptionDSelected: MutableState<Boolean>
) {
    setCorrectOption(optionB)
    isOptionBSelected.value = true
    setAtoFalse(isOptionASelected)
    setCtoFalse(isOptionCSelected)
    setDtoFalse(isOptionDSelected)
}

private fun selectC(
    setCorrectOption: (String) -> Unit,
    optionC: String,
    isOptionCSelected: MutableState<Boolean>,
    isOptionASelected: MutableState<Boolean>,
    isOptionBSelected: MutableState<Boolean>,
    isOptionDSelected: MutableState<Boolean>
) {
    setCorrectOption(optionC)
    selectCorrectOptionField(isOptionCSelected, isOptionASelected, isOptionBSelected, isOptionDSelected)
}

private fun selectCorrectOptionField(
    isOptionCSelected: MutableState<Boolean>,
    isOptionASelected: MutableState<Boolean>,
    isOptionBSelected: MutableState<Boolean>,
    isOptionDSelected: MutableState<Boolean>
) {
    isOptionCSelected.value = true
    setAtoFalse(isOptionASelected)
    setBtoFalse(isOptionBSelected)
    setDtoFalse(isOptionDSelected)
}

private fun selectD(
    setCorrectOption: (String) -> Unit,
    optionD: String,
    isOptionDSelected: MutableState<Boolean>,
    isOptionASelected: MutableState<Boolean>,
    isOptionBSelected: MutableState<Boolean>,
    isOptionCSelected: MutableState<Boolean>
) {
    setCorrectOption(optionD)
    isOptionDSelected.value = true
    setAtoFalse(isOptionASelected)
    setBtoFalse(isOptionBSelected)
    setCtoFalse(isOptionCSelected)
}

private fun clearFields(
    isOptionDSelected: MutableState<Boolean>,
    isOptionASelected: MutableState<Boolean>,
    isOptionBSelected: MutableState<Boolean>,
    isOptionCSelected: MutableState<Boolean>
) {
    setAtoFalse(isOptionASelected)
    setBtoFalse(isOptionBSelected)
    setCtoFalse(isOptionCSelected)
    setDtoFalse(isOptionDSelected)
}

private fun setDtoFalse(isOptionDSelected: MutableState<Boolean>) {
    isOptionDSelected.value = false
}

private fun setAtoFalse(isOptionASelected: MutableState<Boolean>) {
    isOptionASelected.value = false
}

private fun setBtoFalse(isOptionBSelected: MutableState<Boolean>) {
    isOptionBSelected.value = false
}

private fun setCtoFalse(isOptionCSelected: MutableState<Boolean>) {
    isOptionCSelected.value = false
}
