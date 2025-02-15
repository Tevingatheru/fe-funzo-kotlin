package com.example.fe_funzo.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.data.response.UserCountResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClient
import com.example.fe_funzo.infa.client.retrofit.UserClient
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.ui.theme.DashboardView
import kotlinx.coroutines.runBlocking

class AdminDashboard : ComponentActivity() {

    companion object {
        internal const val TAG = "AdminDashboards"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userClient: UserClient = RetrofitClient.createClient(UserClient::class.java)
        val userCount: MutableState<Int> = mutableIntStateOf(0)
        val dashboardView: DashboardView = DashboardView()

        runBlocking {
            val response: UserCountResponse = userClient.getUserCount()
            userCount.value = response.totalCount
        }

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        dashboardView.DashboardCard(
                            label = "Users",
                            value = userCount.value.toString(),
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview5() {
    val dashboardView: DashboardView = DashboardView()
    Fe_funzoTheme {
        dashboardView.DashboardCard(
            label = "Users",
            value = "5",
        )
    }
}