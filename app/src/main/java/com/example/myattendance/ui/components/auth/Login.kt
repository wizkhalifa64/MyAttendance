package com.example.myattendance.ui.components.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogInScreen(navigate: NavHostController) {
    val viewModel = viewModel<LoginHandler>()
    val state = viewModel.loginState
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is LoginHandler.ValidationEvent.Success -> {

                }
            }
        }
    }
    Scaffold {
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
        var mExpanded by remember { mutableStateOf(false) }
        val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown
        val mCities =
            listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Sign In", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(value = state.name,
                onValueChange = {
                    viewModel.loginChangeHandler(LoginOrgFormEvent.organizationNameChange(it))
                },
                leadingIcon = { Icon(imageVector = Icons.Rounded.Person, contentDescription = "") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.nameError != null,
                label = { Text(text = "Name") },
                supportingText = { Text(text = if (state.nameError !== null) state.nameError else "") })

            OutlinedTextField(value = state.email,
                onValueChange = {
                    viewModel.loginChangeHandler(LoginOrgFormEvent.emailChange(it))
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
                supportingText = { Text(text = if (state.emailError !== null) state.emailError else "") })
            OutlinedTextField(value = state.location,
                onValueChange = { viewModel.loginChangeHandler(LoginOrgFormEvent.locationChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn, contentDescription = ""
                    )
                },
                readOnly = true,
                label = { Text("Location") },
                trailingIcon = {
                    Icon(icon, "contentDescription", Modifier.clickable { mExpanded = !mExpanded })
                })
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.loginChangeHandler(LoginOrgFormEvent.passwordChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Lock, contentDescription = ""
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                label = { Text("Password") },
            )
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                mCities.forEach { label ->
                    DropdownMenuItem(text = { Text(text = label) }, onClick = {
                        viewModel.loginChangeHandler(LoginOrgFormEvent.locationChange(label))
                        mExpanded = false
                    })
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { navigate.navigate("homeScreen") },
                    modifier = Modifier.weight(weight = 0.5f)
                ) {
                    Text(text = "Sign In")
                }
            }
        }
    }
}
