package com.example.myattendance.ui.components.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Login {
    private val viewModel = LogInViewModel();

    class LogInViewModel {
        var username by mutableStateOf("")
            private set
        var password by mutableStateOf("")
            private set

        fun updateUsername(input: String) {
            username = input
        }

        fun updatePassword(input: String) {
            password = input
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun LogInScreen() {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "",
                    modifier = Modifier.size(45.dp)
                )
                Divider(modifier = Modifier.weight(0.7f), thickness = 1.dp)
            }
            Row {
                Spacer(modifier = Modifier.height(45.dp))
            }
            Row {
                Text(
                    text = "Log in to your account",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row {
                Text(
                    text = "Welcome back! Please enter your details",
                    color = Color.Gray
                )
            }
            Row {
                Spacer(modifier = Modifier.height(25.dp))
            }
            Row {
                Column {
                    Row(modifier = Modifier.padding(bottom = 10.dp)) {
                        Text(text = "Email")
                    }
                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFF272728)
                            )
                            .padding(12.dp), horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.MailOutline,
                            contentDescription = ""
                        )
                        BasicTextField2(
                            value = viewModel.username,
                            onValueChange = { username ->
                                viewModel.updateUsername(username)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = TextStyle(fontSize = 15.sp, color = Color.LightGray)
                        )
                    }
                    Row {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    Row(modifier = Modifier.padding(bottom = 10.dp)) {
                        Text(text = "Password")
                    }
                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFF272728)
                            )
                            .padding(12.dp), horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = ""
                        )
                        BasicTextField2(
                            value = viewModel.password,
                            onValueChange = { password ->
                                viewModel.updatePassword(password)
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = TextStyle(fontSize = 15.sp, color = Color.LightGray)
                        )
                    }
                    Row {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    Row {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(Color.LightGray)
                        ) {
                            Text(text = "Log In")
                        }
                    }
                }
            }
        }
    }
}