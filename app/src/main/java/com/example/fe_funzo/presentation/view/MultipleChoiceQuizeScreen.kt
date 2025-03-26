package com.example.fe_funzo.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultipleChoiceQuizScreen(
    question: String = "What is your answer?",
    options: List<String> = listOf("Option A", "Option B", "Option C", "Option D"),
    onSubmit: () -> Unit = {}
) {
    var selectedOption = remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LinearProgressIndicator(
                progress = 0f,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Progress",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }

        options.forEachIndexed { index, option ->
            DisplayOptionItem(option, selectedOption, index)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Composable
private fun DisplayOptionItem(
    option: String,
    selectedOption: MutableState<Int?>,
    index: Int
) {
    Text(
        text = option,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(
                if (selectedOption.value!! == index) {
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                } else {
                    MaterialTheme.colorScheme.surface
                }
            )
            .clickable(
                onClick = { selectedOption.value = index },
            )
            .padding(15.dp),
        color = if (selectedOption.value == index) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        fontSize = 18.sp,
    )
}