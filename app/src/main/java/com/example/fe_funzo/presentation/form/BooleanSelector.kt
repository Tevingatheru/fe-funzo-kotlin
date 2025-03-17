package com.example.fe_funzo.presentation.form

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val TAG: String = "BooleanSelector"

@Composable
fun BooleanSelector(
    label: String = "Selection",
    modifier: Modifier = Modifier,
    selectedOption: Boolean,
    setSelectedOption: (Boolean) -> Unit,
    onSubmit:() -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            listOf(true to "True", false to "False").forEach { (optionValue, displayText) ->
                SelectTrueFalseOptionScreen(
                    selectedOption = selectedOption == optionValue,
                    displayText = displayText,
                    onSelect = {
                        setSelectedOption(!selectedOption)
                        Log.i(TAG, "Change choice. To: $selectedOption For option: $optionValue")
                    }
                )
            }

            Button(onClick = {
                onSubmit()
            }) {
                Text(text = "Save")
            }
        }
    }
}


@Composable
private fun SelectTrueFalseOptionScreen(
    selectedOption: Boolean,
    displayText: String,
    onSelect: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedOption,
            onClick = {
                onSelect(true)
            }
        )
        Text(
            text = displayText,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
