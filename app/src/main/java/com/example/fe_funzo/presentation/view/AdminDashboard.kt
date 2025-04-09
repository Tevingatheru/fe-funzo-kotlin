package com.example.fe_funzo.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.view_model.BIViewModel
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.ui.theme.DashboardView

class AdminDashboard : ComponentActivity() {

    companion object {
        internal const val TAG = "AdminDashboards"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bIViewModel: BIViewModel = BIViewModel()

        val userCount: MutableState<Int> = bIViewModel.getUserCount()

        val dashboardView: DashboardView = DashboardView()

        val context : AdminDashboard = this
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Button(onClick = {
                            NavigationUtil.navigateToLandingPage(context = context)
                        }) {
                            Text("Back")
                        }
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