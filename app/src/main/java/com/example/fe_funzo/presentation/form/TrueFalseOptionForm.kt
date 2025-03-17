package com.example.fe_funzo.presentation.form

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.retrofit.request.AddOptionRequest
import com.example.fe_funzo.logic.service.client.impl.OptionClientServiceImpl
import com.example.fe_funzo.logic.view_model.AddOptionViewModel
import kotlinx.coroutines.runBlocking

private const val TAG: String = "TrueFalseOptionForm"

@Composable
fun TrueFalseOptionForm(addOptionViewModel: AddOptionViewModel,) {
    var (selectedOption, setSelectedOption) = remember { mutableStateOf(false) }
    Text("Select the Correct Option")
    BooleanSelector(
        label = "Select correct option.",
        modifier = Modifier.padding(16.dp),
        selectedOption = selectedOption,
        setSelectedOption = {
            setSelectedOption(it)
        },
        onSubmit = {
            addOption(selectedOption, addOptionViewModel)
        }
    )
}

private fun addOption(
    selectedOption: Boolean,
    addOptionViewModel: AddOptionViewModel
) {
    Log.i(TAG, "selectedOption: $selectedOption")
    val optionClientServiceImpl: OptionClientServiceImpl = OptionClientServiceImpl()

    runBlocking {
        val addOptionRequest = AddOptionRequest(
            optionA = null,
            optionB = null,
            optionC = null,
            optionD = null,
            correctOption = selectedOption.toString(),
            questionCode = addOptionViewModel.getQuestion().code,
        )
        optionClientServiceImpl.addOptionRequest(createAddOptionRequest = addOptionRequest)
    }
}

