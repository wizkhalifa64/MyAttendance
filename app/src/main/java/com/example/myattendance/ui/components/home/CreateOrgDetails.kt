package com.example.myattendance.ui.components.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateOrgDetails(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            OutlinedTextField(
                value = "",
                onValueChange = {
//                            viewModel.onEvent(OrgDetailsFormEvent.OrgNameChange(it))
                },
                modifier = Modifier.fillMaxWidth(),
//                        isError = state.orgNameError != null,
                label = { Text(text = "Organization Name") },
//                        supportingText = { Text(text = if (state.orgNameError !== null) state.orgNameError else "") }
            )

            OutlinedTextField(
                value = "",
                onValueChange = {
//                            viewModel.onEvent(OrgDetailsFormEvent.EmployeeCountChange(it))
                },
                modifier = Modifier.fillMaxWidth(),
//                        isError = state.employeeError != null,
                label = { Text(text = "Est Employees") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        supportingText = { Text(text = if (state.employeeError !== null) state.employeeError else "") }
            )
        }
        Button(onClick = {
navController.navigate("homeScreen")
        }) {
            Text("Back")
        }
    }
}