package com.example.myattendance.ui.components.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myattendance.ui.components.validation.ValidateString
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginHandler(
    private val validateString: ValidateString = ValidateString(),
) : ViewModel() {
    var loginState by mutableStateOf(LoginHandlerData())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun loginChangeHandler(event: LoginOrgFormEvent) {
        when (event) {
            is LoginOrgFormEvent.emailChange -> {
                loginState = loginState.copy(email = event.email)
            }

            is LoginOrgFormEvent.passwordChange -> {
                loginState = loginState.copy(password = event.password)
            }

            is LoginOrgFormEvent.locationChange -> {
                loginState = loginState.copy(location = event.location)
            }

            is LoginOrgFormEvent.organizationNameChange -> {
                loginState = loginState.copy(name = event.name)
            }

            is LoginOrgFormEvent.submit -> {
                submitLogin()
            }
        }
    }

    private fun submitLogin() {
        val name = validateString.execute(loginState.name)
        val location = validateString.execute(loginState.location)
        val email = validateString.execute(loginState.email)
        val password = validateString.execute(loginState.password)
        val hasError = listOf(name, email, password, location).any { !it.successful }
        if (hasError) {
            loginState = loginState.copy(
                nameError = name.errorMessage,
                emailError = email.errorMessage,
                passwordError = password.errorMessage,
                locationError = location.errorMessage
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