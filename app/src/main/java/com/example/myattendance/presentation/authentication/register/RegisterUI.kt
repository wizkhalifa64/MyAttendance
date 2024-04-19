package com.example.myattendance.presentation.authentication.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterUI() {
    val viewModel = viewModel<RegisterViewModel>()
    val state = viewModel.registerState
    LaunchedEffect(key1 = state) {
        
    }
    Column(modifier = Modifier.padding(20.dp)) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Register",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier.padding(30.dp)) {
            OutlinedTextField(value = state.name,
                onValueChange = { viewModel.handleChange(RegisterFormEvent.HandleNameChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Full Name")
                },
                supportingText = { state.nameError?.let { Text(text = state.nameError) } })
            OutlinedTextField(value = state.email,
                onValueChange = { viewModel.handleChange(RegisterFormEvent.HandleEmailChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Email")
                }
            ,supportingText = { state.emailError?.let { Text(text = state.emailError) } })
            OutlinedTextField(value = state.password,
                onValueChange = { viewModel.handleChange(RegisterFormEvent.HandlePasswordChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Password")
                },
                supportingText = { state.passwordError?.let { Text(text = state.passwordError) } })
            OutlinedTextField(value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Location")
                },
                supportingText = {state.locationError?.let { Text(text = state.locationError) }}
            )
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                Button(onClick = {viewModel.handleChange(RegisterFormEvent.HandleRegisterSubmit)}) {
                    Text(text = "Let's start")
                }
            }
        }
    }
}