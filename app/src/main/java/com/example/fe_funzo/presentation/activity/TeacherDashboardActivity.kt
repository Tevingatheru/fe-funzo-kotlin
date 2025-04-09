package com.example.fe_funzo.presentation.activity

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
import com.example.fe_funzo.logic.view_model.TeacherAnalyticsViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.ui.theme.DashboardView

class TeacherDashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val teacherAnalyticsModelView: TeacherAnalyticsViewModel = TeacherAnalyticsViewModel()
        val context: TeacherDashboardActivity = this

        teacherAnalyticsModelView.setTeacherDashboardActivityContext(context = context)

        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.padding(innerPadding)){
                        TeacherDashboardView(teacherAnalyticsModelView = teacherAnalyticsModelView)
                    }
                }
            }
        }
    }
}

@Composable
fun TeacherDashboardView(teacherAnalyticsModelView: TeacherAnalyticsViewModel) {
    val dashboardView: DashboardView = DashboardView()
    val teacherStatsResponse = teacherAnalyticsModelView.getTeacherStats()

    val totalNoExamsCreate = teacherStatsResponse.examAverages.size
    Text(
        text = "Welcome to Teachers Dashboard!",
    )

    Button(onClick = {
        NavigationUtil.navigateToLandingPage(context = teacherAnalyticsModelView.getContext())
    }) {
        Text("Back")
    }
    dashboardView.DashboardCard(
        label = "Exams created",
        value = totalNoExamsCreate.toString(),
    )

    dashboardView.DashboardCard(
        label = "Total Average Performance",
        value = teacherStatsResponse.totalPerformanceAverage.toString(),
    )

    teacherStatsResponse.examAverages.forEach {
        dashboardView.DashboardCard(
            label = it.examName,
            value = it.averageScoreOfTotalAttempts.toString(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    Fe_funzoTheme {
        TeacherDashboardView(teacherAnalyticsModelView = TeacherAnalyticsViewModel())
    }
}