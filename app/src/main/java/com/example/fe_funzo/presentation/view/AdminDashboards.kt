package com.example.fe_funzo.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fe_funzo.data.response.UserCountResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClient
import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import kotlinx.coroutines.runBlocking

class AdminDashboards : ComponentActivity() {

    companion object {
        internal const val TAG = "AdminDashboards"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val userClient: UserClient = RetrofitClient.createClient(UserClient::class.java)

        val userCount: MutableState<Int> = mutableStateOf(0)
        runBlocking {
            val response: UserCountResponse = userClient.getUserCount()
            userCount.value = response.totalCount
        }

        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        DashboardCard(
                            title = "Users",
                            value = userCount.value.toString(),
                            onClick = {
                                Log.i(TAG,"")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    value: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleSmall)
            Text(value, style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview5() {
    Fe_funzoTheme {
        DashboardCard(
            title = "Users",
            value = "5",
            onClick = {
                Log.i(AdminDashboards.TAG,"")
            }
        )
    }
}