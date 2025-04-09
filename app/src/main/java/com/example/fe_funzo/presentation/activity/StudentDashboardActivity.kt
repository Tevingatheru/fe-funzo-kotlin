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
import com.example.fe_funzo.logic.view_model.StudentAnalyticsModelView
import com.example.fe_funzo.presentation.activity.ui.theme.Fe_funzoTheme
import com.example.fe_funzo.ui.theme.DashboardView

class StudentDashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentAnalyticsModelView: StudentAnalyticsModelView = StudentAnalyticsModelView()
        val context : StudentDashboardActivity = this

        studentAnalyticsModelView.setContext(context = context)
        enableEdgeToEdge()
        setContent {
            Fe_funzoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        StudentDashboardScreen( studentAnalyticsModelView = studentAnalyticsModelView)
                    }
                }
            }
        }
    }
}

@Composable
fun StudentDashboardScreen( studentAnalyticsModelView: StudentAnalyticsModelView) {
    val dashboardView: DashboardView = DashboardView()
    val studentAnalytics = studentAnalyticsModelView.getStudentAnalytics()

    Text(
        text = "Hello Student Dashboard!",
    )

    Button(onClick = {
        NavigationUtil.navigateToLandingPage(context = studentAnalyticsModelView.getContext())
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
        StudentDashboardScreen(StudentAnalyticsModelView())
    }
}