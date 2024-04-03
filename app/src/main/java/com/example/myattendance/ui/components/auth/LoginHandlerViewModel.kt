package com.example.myattendance.ui.components.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.presentation.validation.ValidateEmail
import com.example.myattendance.presentation.validation.ValidateString
import com.example.myattendance.presentation.validation.ValidationResult
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
    fun loginChangeHandler(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChange -> {
                loginState = loginState.copy(
                    email = event.email
                )
                val check = errorHandler(validateEmail.execute(event.email))
                if (!check.toBoolean()) {
                    loginState = loginState.copy(emailError = check)
                }
            }

            is LoginFormEvent.PasswordChange -> {
                loginState = loginState.copy(
                    password = event.password
                )
                val check = errorHandler(validateString.execute(event.password))
                if (!check.toBoolean()) {
                    loginState = loginState.copy(passwordError = check)
                }
            }

            is LoginFormEvent.OrganizationLogin -> {
//                Log.d("Admin","check")
                submitAuthLogin()
            }

            is LoginFormEvent.EmployeeLogin -> {
//                Log.d("Employee","check")
                submitEmployeeAuthLogin()
            }
        }
    }

    private fun submitAuthLogin() {
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

    private fun submitEmployeeAuthLogin() {
        val email = validateString.execute(loginState.email)
        val password = validateString.execute(loginState.password)
        val hasError = listOf(email, password).any { !it.successful }
        if (hasError) {
            loginState = loginState.copy(
                emailError = email.errorMessage,
                passwordError = password.errorMessage,
            )
//            Log.d("Test","Err")
            return
        }
        viewModelScope.launch {
            try {
//                Log.d("Test","hit")
                val res = AuthService().employeeLogin(
                    loginState.email,
                    loginState.password,

                    )
                if (res.hasErrors()) {
                    res.errors?.get(0)?.message?.let {
                        validationEventChannel.send(
                            ValidationEvent.Error(it)
                        )
                    }

                }
                res.data?.loginEmployee?.let {
                    validationEventChannel.send(
                        ValidationEvent.Success(it.token)
                    )
                }

            } catch (e: ApolloException) {
                e.message?.let { validationEventChannel.send(ValidationEvent.Error(it)) }
            }
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