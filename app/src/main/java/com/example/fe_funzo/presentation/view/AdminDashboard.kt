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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_funzo.infa.util.NavigationUtil
import com.example.fe_funzo.logic.view_model.BIViewModel
import com.example.fe_funzo.presentation.view.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.ui.theme.DashboardView

class AdminDashboard : ComponentActivity() {

    companion object {
        private const val TAG = "AdminDashboards"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bIViewModel: BIViewModel = BIViewModel()
        bIViewModel.getUserStats()
        val dashboardView: DashboardView = DashboardView()
        val context : AdminDashboard = this
        bIViewModel.getExamStats(context = context)

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
                        ShowExamStats(dashboardView, bIViewModel)
                        ShowUserStats(dashboardView, bIViewModel)
                    }
                }
            }
        }
    }

    @Composable
    private fun ShowExamStats(dashboardView: DashboardView,
                              bIViewModel: BIViewModel) {
        val examAverages = bIViewModel.getExamAverageResponse()

        examAverages.forEach{
            dashboardView.DashboardCard(
                label = it.examName,
                value = it.averageScoreOfTotalAttempts.toString()
            )
        }
    }

    @Composable
    private fun ShowUserStats(
        dashboardView: DashboardView,
        bIViewModel: BIViewModel
    ) {
        dashboardView.DashboardCard(
            label = "Users",
            value = bIViewModel.getTotalUserCount().toString()
        )
        dashboardView.DashboardCard(
            label = "Admins",
            value = bIViewModel.getTotalAdminCount().toString(),
        )
        dashboardView.DashboardCard(
            label = "Teachers",
            value = bIViewModel.getTotalTeacherCount().toString(),
        )
        dashboardView.DashboardCard(
            label = "Students",
            value = bIViewModel.getTotalStudentCount().toString(),
        )
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