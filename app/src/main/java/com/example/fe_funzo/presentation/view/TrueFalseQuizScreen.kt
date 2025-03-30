package com.example.fe_funzo.presentation.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val TAG: String = "TrueFalseQuizScreen"

@Composable
fun TrueFalseQuizScreen(
    question: String = "Can you see this question?",
    onSubmit: (selectAnswer: Boolean?) -> Unit = {},
) {
    var (selectedAnswer, setSelectedAnswer) = remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.headlineMedium
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
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(true to "True", false to "False").forEach { (key, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = { setSelectedAnswer(key) },
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedAnswer == key,
                        onClick = {
                            Log.i(TAG, "Before selectedAnswer: $selectedAnswer, \n key: $key")
                            setSelectedAnswer(key)
                            Log.i(TAG, "After selectedAnswer: $selectedAnswer, \n" +
                                    " key: $key")
                                  },

                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(text = value)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                onSubmit(selectedAnswer)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}