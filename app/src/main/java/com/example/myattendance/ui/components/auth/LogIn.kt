package com.example.myattendance.ui.components.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogInScreen(role: String?, snackBarHostState: SnackbarHostState) {
    val viewModel = viewModel<LoginHandlerViewModel>()
    val state = viewModel.loginState
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is LoginHandlerViewModel.ValidationEvent.Success -> {
                    scope.launch {
//                        snackBarHostState.showSnackbar(
//                            message = "Success", duration = SnackbarDuration.Indefinite
//                        )
                    }

                }

                is LoginHandlerViewModel.ValidationEvent.Error -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = event.err, duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }
    Text(text = "Login", style = MaterialTheme.typography.displaySmall)
    Text(
        text = "Welcome back! Please enter your details",
        style = MaterialTheme.typography.labelLarge
    )
    Spacer(modifier = Modifier.height(30.dp))
    OutlinedTextField(value = state.email,
        onValueChange = {
            viewModel.loginChangeHandler(LoginFormEvent.EmailChange(it))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.MailOutline, contentDescription = ""
            )
        },
        modifier = Modifier.fillMaxWidth(),
        isError = state.emailError != null,
        label = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        supportingText = { state.emailError?.let { Text(text = state.emailError) } })
    OutlinedTextField(value = state.password,
        onValueChange = {
            viewModel.loginChangeHandler(
                LoginFormEvent.PasswordChange(
                    it
                )
            )
        },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Lock, contentDescription = ""
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = state.passwordError != null,
        label = { Text("Password") },
        supportingText = { state.passwordError?.let { Text(text = state.passwordError) } })
    Spacer(modifier = Modifier.height(12.dp))
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                if (role != null) {
                    Log.d("Hit check",role)
                }
                if (role == "ADMIN") {
                    viewModel.loginChangeHandler(LoginFormEvent.OrganizationLogin)
                } else {
                    viewModel.loginChangeHandler(
                        LoginFormEvent.EmployeeLogin
                    )
                } /* navigate.navigate("homeScreen") */
            }, modifier = Modifier.weight(weight = 0.5f)
        ) {
            Text(text = "Login")
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp, top = 15.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .weight(0.3f)
        )
        Text(text = "Or", modifier = Modifier.padding(start = 5.dp, end = 5.dp))
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .weight(0.3f)
        )
    }

}
