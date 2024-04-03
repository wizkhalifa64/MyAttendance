package com.example.myattendance.presentation.authentication.register

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterUI(){

    Text(text = "Register", style = MaterialTheme.typography.displaySmall)
    Text(
        text = "Let's start! Please enter your details", style = MaterialTheme.typography.labelLarge
    )
    Spacer(modifier = Modifier.height(30.dp))

}