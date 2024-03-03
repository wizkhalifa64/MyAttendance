package com.example.myattendance.ui.components.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier

                    .padding(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0XFF232526), Color(0XFF414345)
                                )
                            ), shape = RoundedCornerShape(15.dp)
                        )
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Good Evening", style = MaterialTheme.typography.headlineSmall)
                    Text(text = "Ujjwal", style = MaterialTheme.typography.displaySmall)
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Looks like you have not created Organization",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { showBottomSheet.value = true }) {
                    Text(text = "Lets Create")
                }
            }
        }
        if (showBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet.value = false
                }, sheetState = sheetState
            ) {
                val viewModel = viewModel<CreateOrganization>()
                val state = viewModel.state
                val context = LocalContext.current
                LaunchedEffect(key1 = context) {
                    viewModel.validationEvents.collect { event ->
                        when (event) {
                            is CreateOrganization.ValidationEvent.Success -> {
                                Toast.makeText(context, "Organization Created", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {

                    OutlinedTextField(value = state.orgName,
                        onValueChange = {
                            viewModel.onEvent(OrgDetailsFormEvent.OrgNameChange(it))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        isError = state.orgNameError != null,
                        label = { Text(text = "Organization Name") },
                        supportingText = { Text(text = if (state.orgNameError !== null) state.orgNameError else "") })

                    OutlinedTextField(value = state.employeeCount,
                        onValueChange = {
                            viewModel.onEvent(OrgDetailsFormEvent.EmployeeCountChange(it))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        isError = state.employeeError != null,
                        label = { Text(text = "Est Employees") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        supportingText = { Text(text = if (state.employeeError !== null) state.employeeError else "") })
                }
                Button(onClick = {

                }) {
                    Text("Hide bottom sheet")
                }
            }
        }
    }
}