package com.example.myattendance.ui.components.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthUi(navController: NavHostController, role: String?) {
    val type = remember {
        mutableStateOf("register")
    }
//    if (string != null) {
//        Log.d("direction",string)
//    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            if (role == "EMPLOYEE") {
                LogInScreen(role)
            } else {
                if (type.value == "register") {
                    RegisterScreen()
                } else {
                    LogInScreen(role)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Log in your account")
                    TextButton(onClick = {
                        type.value = if (type.value == "register") "login" else "register"
                    }) {
                        Text(text = if (type.value == "register") "Log in" else "Register")
                    }
                }
            }


        }


    }
}