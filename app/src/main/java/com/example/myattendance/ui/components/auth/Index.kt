package com.example.myattendance.ui.components.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myattendance.presentation.navhandler.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Index(navController: NavHostController) {
    Scaffold {
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
           Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
               Button(onClick = {navController.navigate(Screens.Auth.route+"ADMIN")}) {
                   Text(text = "Admin")
               }
               Button(onClick = { navController.navigate(Screens.Auth.route+"EMPLOYEE")}) {
                   Text(text = "Employee")
               }
           }
        }
    }
}