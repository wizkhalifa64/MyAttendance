package com.example.myattendance.navhandler

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myattendance.ui.components.auth.LogInScreen
import com.example.myattendance.ui.components.home.CreateOrgDetails
import com.example.myattendance.ui.components.home.HomeScreen

@Composable
fun NavHandler() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "auth") {
        composable("auth") { LogInScreen(navController) }
        composable("createOrgDetails") { CreateOrgDetails(navController) }
        composable("homeScreen") { HomeScreen(navController) }
    }
}

