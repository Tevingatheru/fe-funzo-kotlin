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
import com.example.fe_funzo.logic.view_model.StudentAnalyticsViewModel
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.ui.theme.DashboardView

class StudentDashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentAnalyticsViewModel: StudentAnalyticsViewModel = StudentAnalyticsViewModel()
        val context : StudentDashboardActivity = this

        studentAnalyticsViewModel.setContext(context = context)
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        StudentDashboardScreen( studentAnalyticsViewModel = studentAnalyticsViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun StudentDashboardScreen(studentAnalyticsViewModel: StudentAnalyticsViewModel) {
    val dashboardView: DashboardView = DashboardView()
    val studentAnalytics = studentAnalyticsViewModel.getStudentAnalytics()


    Text(
        text = "Hello Student Dashboard!",
    )

    Button(onClick = {
        NavigationUtil.navigateToLandingPage(context = studentAnalyticsViewModel.getContext())
    }) {
        Text("Back")
    }
    dashboardView.DashboardCard(
        label = "Total Average Score",
        value = studentAnalytics.totalAverageScore.toString(),
    )
    studentAnalytics.examAverages.forEach {
        dashboardView.DashboardCard(
            label = it.examName,
            value = it.averageScoreOfTotalAttempts.toString(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    Fe_funzoTheme {
        StudentDashboardScreen(StudentAnalyticsViewModel())
    }
}