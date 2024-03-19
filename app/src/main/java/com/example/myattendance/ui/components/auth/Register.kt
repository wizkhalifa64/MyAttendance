package com.example.myattendance.ui.components.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myattendance.datahandler.DataHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(snackBarHostState: SnackbarHostState) {
    val viewModel = viewModel<RegisterHandlerViewModel>()
    val state = viewModel.registerState
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = DataHandler(context)
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is RegisterHandlerViewModel.ValidationEvent.Success -> {
                    scope.launch {
                        dataStore.setToken(event.token)
                    }
                }

                is RegisterHandlerViewModel.ValidationEvent.Error -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = event.err, duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }


    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val countryList = listOf(
        mapOf("label" to "India", "value" to "91"),
        mapOf("label" to "United States OF America", "value" to "110"),
        mapOf("label" to "India", "value" to "91"),
        mapOf("label" to "United States OF America", "value" to "110"),
        mapOf("label" to "India", "value" to "91"),
        mapOf("label" to "United States OF America", "value" to "110"),
        mapOf("label" to "India", "value" to "91"),
        mapOf("label" to "United States OF America", "value" to "110")
    )
    Text(text = "Register", style = MaterialTheme.typography.displaySmall)
    Text(
        text = "Let's start! Please enter your details", style = MaterialTheme.typography.labelLarge
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
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.LocationOn, contentDescription = ""
            )
        },
        isError = state.locationError != null,
        readOnly = true,
        label = { Text("Location") },
        trailingIcon = {
            Icon(Icons.Filled.KeyboardArrowDown, "", Modifier.clickable { showBottomSheet = true })
        },
        supportingText = { state.locationError?.let { Text(text = state.locationError) } })



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

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            }, sheetState = sheetState
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            countryList.forEach { label ->
                DropdownMenuItem(text = { label["label"]?.let { it1 -> Text(text = it1) } },
                    onClick = {
                        viewModel.registerChangeHandler(
                            RegisterOrgFormEvent.LocationChange(
                                label
                            )
                        )
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    })
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = { viewModel.registerChangeHandler(RegisterOrgFormEvent.Submit) },
            modifier = Modifier.weight(weight = 0.5f)
        ) {
            Text(text = "Register")
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
