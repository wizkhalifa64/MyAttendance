package com.example.myattendance.ui.components.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.gql.loginFunction
import kotlinx.coroutines.launch

object Login {
    private val viewModel = LogInViewModel();

    class LogInViewModel {
        var email by mutableStateOf("")
            private set
        var password by mutableStateOf("")
            private set

        fun updateUsername(input: String) {
            email = input
        }

        fun updatePassword(input: String) {
            password = input
        }

        suspend fun handleLogIn(
            snackBarHostState: SnackbarHostState, loading: MutableState<Boolean>
        ) {
            try {
                val res = loginFunction(email, password);
                loading.value = false
                if (res.hasErrors()) {
                    snackBarHostState.showSnackbar(res.errors?.get(0)?.message.toString())
                }
            } catch (e: ApolloException) {
                loading.value = false
                snackBarHostState.showSnackbar(e.message.toString())
            }

        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun LogInScreen() {
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }
        val loading = remember {
            mutableStateOf(false)
        }
        Scaffold(snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }) {
            Column(modifier = Modifier.padding(20.dp)) {
                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    text = "Log in to your account",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Welcome back! Please enter your details", color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(85.dp))
                OutlinedTextField(value = viewModel.email,
                    onValueChange = { username ->
                        viewModel.updateUsername(username)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.MailOutline,
                            contentDescription = ""
                        )
                    })
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(value = viewModel.password,
                    onValueChange = { password ->
                        viewModel.updatePassword(password)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Lock, contentDescription = ""
                        )
                    },
                    label = { Text(text = "Password") })
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        scope.launch {
                            loading.value = true
                            viewModel.handleLogIn(snackBarHostState, loading)
                        }
                    },
                    enabled = !loading.value,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = if (loading.value) "Signing in..." else "Sign In",
                        fontWeight = FontWeight.Bold
                    )

                }

            }

        }


    }
}