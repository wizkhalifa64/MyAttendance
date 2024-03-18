package com.example.myattendance.navhandler

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myattendance.MainViewModel
import com.example.myattendance.ui.components.auth.AuthUi
import com.example.myattendance.ui.components.auth.Index
import com.example.myattendance.ui.components.home.HomeScreen

@Composable
fun NavHandler(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.Index.route) {
        composable(Screens.Index.route) { Index(navController) }
        composable(Screens.Auth.route + "{role}") { backStackEntry ->
            AuthUi(
                navController, backStackEntry.arguments?.getString("role")
            )
        }
//        composable("createOrgDetails") { CreateOrgDetails(navController) }
        composable(Screens.Home.route) { HomeScreen(navController) }
    }
}

