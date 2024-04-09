package com.example.myattendance.presentation.authentication.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterUI() {
    Column(modifier = Modifier.padding(20.dp)) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Register",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier.padding(35.dp)) {
            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
        }
    }
}