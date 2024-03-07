package com.example.myattendance.ui.components.home.helper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myattendance.ui.components.validation.ValidateString
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateOrganization(
    private val validateString: ValidateString = ValidateString(),
) : ViewModel() {
    var state by mutableStateOf(OrgDetailsFormState());
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    fun onEvent(event: OrgDetailsFormEvent) {
        when (event) {
            is OrgDetailsFormEvent.OrgNameChange -> {
                state = state.copy(orgName = event.orgName)
            }

            is OrgDetailsFormEvent.EmployeeCountChange -> {
                state = state.copy(employeeCount = event.employeeCount)
            }

            is OrgDetailsFormEvent.StartTimeChange -> {
                state = state.copy(startTime = event.startTime)
            }

            is OrgDetailsFormEvent.EndTimeChange -> {
                state = state.copy(endTime = event.endTime)
            }

            is OrgDetailsFormEvent.submit -> {
                submitFormData()
            }
        }
    }

    private fun submitFormData() {
        val orgName = validateString.execute(state.orgName)
        val employeeCount = validateString.execute(state.employeeCount)
        val startTime = validateString.execute(state.startTime)
        val endTime = validateString.execute(state.endTime)
        val hasError = listOf(
            orgName, employeeCount, startTime, endTime
        ).any { !it.successful }
        if (hasError) {
            state = state.copy(
                orgNameError = orgName.errorMessage,
                employeeError = employeeCount.errorMessage,
                startTimeError = startTime.errorMessage,
                endTimeError = endTime.errorMessage

            )
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
    }
}