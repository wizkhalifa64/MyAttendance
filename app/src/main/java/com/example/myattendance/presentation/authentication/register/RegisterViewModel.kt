package com.example.myattendance.presentation.authentication.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.presentation.validation.ValidateEmail
import com.example.myattendance.presentation.validation.ValidateMap
import com.example.myattendance.presentation.validation.ValidateString
import com.example.myattendance.presentation.validation.ValidationResult
import com.example.myattendance.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val validateString: ValidateString = ValidateString(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateMap: ValidateMap = ValidateMap(),
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {
    var registerState by mutableStateOf(RegisterData())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    fun handleChange(event: RegisterFormEvent) {
        when (event) {
            is RegisterFormEvent.HandleEmailChange -> {
                registerState = registerState.copy(
                    email = event.email
                )
                val errorMessage = errorMessageHandler(validateEmail.execute(event.email))
                if (!errorMessage.isNullOrBlank()) {
                    registerState = registerState.copy(
                        emailError = errorMessage
                    )
                }
            }

            is RegisterFormEvent.HandleLocationChange -> {
                registerState = registerState.copy(
                    location = event.location
                )
                val errorMessage = errorMessageHandler(validateMap.execute(event.location))
                if (!errorMessage.isNullOrBlank()) {
                    registerState = registerState.copy(
                        locationError = errorMessage
                    )
                }
            }

            is RegisterFormEvent.HandleNameChange -> {
                registerState = registerState.copy(
                    name = event.name
                )
                val errorMessage = errorMessageHandler(validateString.execute(event.name))
                if (!errorMessage.isNullOrBlank()) {
                    registerState = registerState.copy(
                        nameError = errorMessage
                    )
                }
            }

            is RegisterFormEvent.HandlePasswordChange -> {
                registerState = registerState.copy(
                    password = event.password
                )
                val errorMessage = errorMessageHandler(validateString.execute(event.password))
                if (!errorMessage.isNullOrBlank()) {
                    registerState = registerState.copy(
                        passwordError = errorMessage
                    )
                }
            }

            is RegisterFormEvent.HandleRegisterSubmit -> {
                register()
            }
        }
    }

    private fun errorMessageHandler(result: ValidationResult): String? {
        return if (result.successful) {
            null
        } else {
            result.errorMessage
        }
    }

    private fun register() {
        val nameErr = validateString.execute(registerState.name)
        val locationErr = validateMap.execute(registerState.location)
        val emailErr = validateString.execute(registerState.email)
        val passwordErr = validateString.execute(registerState.password)

        val isError = listOf(nameErr, locationErr, emailErr, passwordErr).any { !it.successful }
        if (isError) {
            registerState = registerState.copy(
                nameError = nameErr.errorMessage,
                emailError = emailErr.errorMessage,
                passwordError = passwordErr.errorMessage,
                locationError = locationErr.errorMessage
            )
            return
        } else {
            viewModelScope.launch {
                try {
                    val response = authRepository.OrganizationRegisterService(
                        registerState.email,
                        registerState.password,
                        registerState.location,
                        registerState.name
                    )
                    if (response.hasErrors()) {
                        response.errors?.get(0)?.message?.let {
                            validationEventChannel.send(
                                ValidationEvent.Error(it)
                            )
                        }

                    }
                    response.data?.createOrganization?.let {
                        validationEventChannel.send(
                            ValidationEvent.Success(
                                it.token
                            )
                        )
                    }


                } catch (e: ApolloException) {
                    e.message?.let { validationEventChannel.send(ValidationEvent.Error(it)) }
                }
            }
        }
    }

    sealed class ValidationEvent {
        data class Success(val token: String) : ValidationEvent()
        data class Error(val err: String) : ValidationEvent()
    }
}