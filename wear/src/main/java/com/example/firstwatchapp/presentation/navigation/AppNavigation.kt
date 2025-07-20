package com.example.firstwatchapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstwatchapp.presentation.screen.HeartRateScreen
import com.example.firstwatchapp.presentation.screen.MainScreen
import com.example.firstwatchapp.presentation.sensors.HeartRateService

@Composable
fun AppNavigation(heartRate: Float, zone : Int) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("heart_rate") { HeartRateScreen(heartRate, zone) }
    }
}
