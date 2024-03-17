package com.example.myattendance.navhandler

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myattendance.MainViewModel
import com.example.myattendance.ui.components.auth.AuthUi
import com.example.myattendance.ui.components.home.CreateOrgDetails
import com.example.myattendance.ui.components.home.HomeScreen

@Composable
fun NavHandler(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "homeScreen") {
        composable("auth") { AuthUi() }
        composable("createOrgDetails") { CreateOrgDetails(navController) }
        composable("homeScreen") { HomeScreen(navController) }
    }
}

