package com.example.fe_funzo.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        val dashboardView: DashboardView = DashboardView()
        val context: AdminDashboard = this
        bIViewModel.setContext(context = context)

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())) {
                        Button(onClick = {
                            NavigationUtil.navigateToLandingPage(context = context)
                        }) {
                            Text("Back")
                        }

                        ShowUserStats(dashboardView, bIViewModel)
                        ShowExamStats(dashboardView, bIViewModel)
                        ShowSubjectStats(dashboardView = dashboardView, bIViewModel = bIViewModel)
                    }
                }
            }
        }
    }

    @Composable
    private fun ShowSubjectStats(dashboardView: DashboardView,
                                 bIViewModel: BIViewModel) {
        bIViewModel.getSubjectStats()

        StatHeadLine(text = "Subject Analytics")
        dashboardView.DashboardCard(label = "Subjects", value = bIViewModel.getSubjectCount().toString())

        StatHeadLine(text = "Exams per Subject Analytics")
        val examCounts = bIViewModel.getExamCountPerSubjectResponse()
        examCounts.forEach {
            dashboardView.DashboardCard(
                label = it.first,
                value = it.second.toString()
            )
        }
    }

    @Composable
    private fun StatHeadLine(text: String) {
        Text(text = text, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
    }

    @Composable
    private fun ShowExamStats(dashboardView: DashboardView,
                              bIViewModel: BIViewModel) {
        bIViewModel.getExamStats()
        val examAverages = bIViewModel.getExamAverageResponse()
        StatHeadLine(text = "Exam Analytics")
        val examCount = examAverages.size
        dashboardView.DashboardCard(
            label = "Exams",
            value = examCount.toString()
        )
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
        bIViewModel.getUserStats()
        StatHeadLine(text = "User Analytics")

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