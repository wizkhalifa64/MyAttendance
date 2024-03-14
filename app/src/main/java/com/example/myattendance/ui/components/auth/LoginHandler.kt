package com.example.myattendance.ui.components.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myattendance.ui.components.validation.ValidateEmail
import com.example.myattendance.ui.components.validation.ValidateMap
import com.example.myattendance.ui.components.validation.ValidateString
import com.example.myattendance.ui.components.validation.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginHandler(
    private val validateString: ValidateString = ValidateString(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateMap: ValidateMap = ValidateMap()
) : ViewModel() {
    var loginState by mutableStateOf(LoginHandlerData())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun loginChangeHandler(event: LoginOrgFormEvent) {
        when (event) {
            is LoginOrgFormEvent.EmailChange -> {
                loginState = loginState.copy(email = event.email)
                val check = errorHandler(validateEmail.execute(event.email))
                if (!check.toBoolean()) {
                    Log.d("Error",check.toString())
                    loginState = loginState.copy(emailError = check)
                }
            }

            is LoginOrgFormEvent.PasswordChange -> {
                loginState = loginState.copy(password = event.password)
                val check = errorHandler(validateString.execute(event.password))
                if (!check.toBoolean()) {
                    Log.d("Error",check.toString())
                    loginState = loginState.copy(passwordError = check)
                }
            }

            is LoginOrgFormEvent.LocationChange -> {
                loginState = loginState.copy(location = event.location)
                val check = errorHandler(validateMap.execute(event.location))
                if (!check.toBoolean()) {
                    Log.d("Error",check.toString())
                    loginState = loginState.copy(locationError = check)
                }
            }

            is LoginOrgFormEvent.OrganizationNameChange -> {
                loginState = loginState.copy(name = event.name)
                val check = errorHandler(validateString.execute(event.name))
                if (!check.toBoolean()) {
                    Log.d("Error",check.toString())
                    loginState = loginState.copy(nameError = check)
                }
            }

            is LoginOrgFormEvent.Submit -> {
                submitLogin()
            }
        }
    }

    private fun errorHandler(validationResult: ValidationResult): String? {
        return if (validationResult.successful) {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
            }
            null
        } else {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Error)
            }
            validationResult.errorMessage
        }
    }

    private fun submitLogin() {
        val name = validateString.execute(loginState.name)
        val location = validateMap.execute(loginState.location)
        val email = validateString.execute(loginState.email)
        val password = validateString.execute(loginState.password)
        val hasError = listOf(name, email, password, location).any { !it.successful }
        val hasErrorLogin = listOf(email, password).any { !it.successful }
        if (hasError) {
            loginState = loginState.copy(
                nameError = name.errorMessage,
                emailError = email.errorMessage,
                passwordError = password.errorMessage,
                locationError = location.errorMessage
            )
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
            }
        }else{
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Error)
            }
        }

    }

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
        data object Error : ValidationEvent()
    }
}