package com.example.myattendance.ui.components.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogInScreen(navigate: NavHostController) {
    val viewModel = viewModel<RegisterHandlerViewModel>()
    val state = viewModel.registerState
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is RegisterHandlerViewModel.ValidationEvent.Success -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Success", duration = SnackbarDuration.Indefinite
                        )
                    }

                }

                is RegisterHandlerViewModel.ValidationEvent.Error -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = event.err, duration = SnackbarDuration.Indefinite
                        )
                    }
                }
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
        var mExpanded by remember { mutableStateOf(false) }
        val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown
        val mCities = listOf(
            mapOf("label" to "India", "value" to "91"),
            mapOf("label" to "United States OF America", "value" to "110")
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Sign in", style = MaterialTheme.typography.displaySmall)
            Text(
                text = "Welcome back! Please enter your details",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(value = state.name,
                onValueChange = {
                    viewModel.registerChangeHandler(RegisterOrgFormEvent.OrganizationNameChange(it))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Person, contentDescription = ""
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                isError = state.nameError != null,
                label = { Text(text = "Name") },
                supportingText = { state.nameError?.let { Text(text = state.nameError) } })
            OutlinedTextField(value = state.location["label"].toString(),
                onValueChange = {
                    viewModel.registerChangeHandler(
                        RegisterOrgFormEvent.LocationChange(
                            mapOf("label" to "", "value" to it)
                        )
                    )
                },
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
                isError = state.locationError != null,
                readOnly = true,
                label = { Text("Location") },
                trailingIcon = {
                    Icon(icon, "contentDescription", Modifier.clickable { mExpanded = !mExpanded })
                },
                supportingText = { state.locationError?.let { Text(text = state.locationError) } })
            DropdownMenu(expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                mCities.forEach { label ->
                    DropdownMenuItem(text = { label["label"]?.let { it1 -> Text(text = it1) } },
                        onClick = {
                            viewModel.registerChangeHandler(
                                RegisterOrgFormEvent.LocationChange(
                                    label
                                )
                            )
                        })
                }
            }


            OutlinedTextField(value = state.email,
                onValueChange = {
                    viewModel.registerChangeHandler(RegisterOrgFormEvent.EmailChange(it))
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
                    viewModel.registerChangeHandler(
                        RegisterOrgFormEvent.PasswordChange(
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
                    onClick = { viewModel.registerChangeHandler(RegisterOrgFormEvent.Submit) /* navigate.navigate("homeScreen") */ },
                    modifier = Modifier.weight(weight = 0.5f)
                ) {
                    Text(text = "Sign In")
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
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(text = "Log in your account")
//                TextButton(onClick = {
//                    type.value = if (type.value == "signIn") "login" else "signIn"
//                }) {
//                    Text(text = if (type.value == "signIn") "Log in" else "Sign in")
//                }
//            }
        }
    }
}
