package com.example.myattendance.ui.components.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myattendance.ui.components.validation.ValidateEmail
import com.example.myattendance.ui.components.validation.ValidateMap
import com.example.myattendance.ui.components.validation.ValidateString
import com.example.myattendance.ui.components.validation.ValidationResult
import kotlinx.coroutines.launch

class LoginHandlerViewModel(
    private val validateString: ValidateString = ValidateString(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateMap: ValidateMap = ValidateMap()
) : ViewModel() {
    var loginState by mutableStateOf(RegisterHandlerData())
    fun loginChangeHandler(event: LoginOrgFormEvent) {
        when (event) {
            is LoginOrgFormEvent.EmailChange -> {

            }

            is LoginOrgFormEvent.PasswordChange -> {

            }

            is LoginOrgFormEvent.Submit -> {

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
        viewModelScope.launch {}
    }

    private fun errorHandler(validationResult: ValidationResult): String? {
        return if (validationResult.successful) {
            null
        } else {
            validationResult.errorMessage
        }
    }

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
        data class Error(val err: String) : ValidationEvent()
    }
}