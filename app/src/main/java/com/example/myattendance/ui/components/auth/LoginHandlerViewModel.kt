package com.example.myattendance.ui.components.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myattendance.ui.components.validation.ValidateEmail
import com.example.myattendance.ui.components.validation.ValidateString
import com.example.myattendance.ui.components.validation.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginHandlerViewModel(
    private val validateString: ValidateString = ValidateString(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
) : ViewModel() {
    var loginState by mutableStateOf(RegisterHandlerData())
    private val validationEventChannel = Channel<LoginHandlerViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    fun loginChangeHandler(event: LoginOrgFormEvent) {
        when (event) {
            is LoginOrgFormEvent.EmailChange -> {
                loginState = loginState.copy(
                    email = event.email
                )
                val check = errorHandler(validateEmail.execute(event.email))
                if (!check.toBoolean()) {
                    loginState = loginState.copy(emailError = check)
                }
            }

            is LoginOrgFormEvent.PasswordChange -> {
                loginState = loginState.copy(
                    password = event.password
                )
                val check = errorHandler(validateEmail.execute(event.password))
                if (!check.toBoolean()) {
                    loginState = loginState.copy(passwordError = check)
                }
            }

            is LoginOrgFormEvent.Submit -> {
                submitRegister()
            }
        }
    }

    private fun submitRegister() {
        val email = validateString.execute(loginState.email)
        val password = validateString.execute(loginState.password)
        val hasError = listOf(email, password).any { !it.successful }
        if (hasError) {
            loginState = loginState.copy(
                emailError = email.errorMessage,
                passwordError = password.errorMessage,
            )
            return
        }
        viewModelScope.launch {

        }
    }

    private fun errorHandler(validationResult: ValidationResult): String? {
        return if (validationResult.successful) {
            null
        } else {
            validationResult.errorMessage
        }
    }

    sealed class ValidationEvent {
        data class Success(val token: String) : ValidationEvent()
        data class Error(val err: String) : ValidationEvent()
    }
}