package com.example.myattendance.ui.components.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.ui.components.validation.ValidateEmail
import com.example.myattendance.ui.components.validation.ValidateMap
import com.example.myattendance.ui.components.validation.ValidateString
import com.example.myattendance.ui.components.validation.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterHandlerViewModel(
    private val validateString: ValidateString = ValidateString(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateMap: ValidateMap = ValidateMap()
) : ViewModel() {
    var registerState by mutableStateOf(RegisterHandlerData())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun registerChangeHandler(event: RegisterOrgFormEvent) {
        when (event) {
            is RegisterOrgFormEvent.EmailChange -> {
                registerState = registerState.copy(email = event.email)
                val check = errorHandler(validateEmail.execute(event.email))
                if (!check.toBoolean()) {

                    registerState = registerState.copy(emailError = check)
                }
            }

            is RegisterOrgFormEvent.PasswordChange -> {
                registerState = registerState.copy(password = event.password)
                val check = errorHandler(validateString.execute(event.password))
                if (!check.toBoolean()) {
                    registerState = registerState.copy(passwordError = check)
                }
            }

            is RegisterOrgFormEvent.LocationChange -> {
                registerState = registerState.copy(location = event.location)
                val check = errorHandler(validateMap.execute(event.location))
                if (!check.toBoolean()) {
                    registerState = registerState.copy(locationError = check)
                }
            }

            is RegisterOrgFormEvent.OrganizationNameChange -> {
                registerState = registerState.copy(name = event.name)
                val check = errorHandler(validateString.execute(event.name))
                if (!check.toBoolean()) {
                    registerState = registerState.copy(nameError = check)
                }
            }

            is RegisterOrgFormEvent.Submit -> {
                submitRegister()
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

    private fun submitRegister() {
        val name = validateString.execute(registerState.name)
        val location = validateMap.execute(registerState.location)
        val email = validateString.execute(registerState.email)
        val password = validateString.execute(registerState.password)
        val hasError = listOf(name, email, password, location).any { !it.successful }
        if (hasError) {
            registerState = registerState.copy(
                nameError = name.errorMessage,
                emailError = email.errorMessage,
                passwordError = password.errorMessage,
                locationError = location.errorMessage
            )
            return
        }

        viewModelScope.launch {
            try {
                val res = AuthService().register(
                    registerState.email,
                    registerState.password,
                    registerState.location,
                    registerState.name
                )
                if (res.hasErrors()) {
                    res.errors?.get(0)?.message?.let {
                        validationEventChannel.send(
                            ValidationEvent.Error(
                                it
                            )
                        )
                    }

                }
                res.data?.createOrganization?.let {
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

    sealed class ValidationEvent {
        data class Success(val token: String) : ValidationEvent()
        data class Error(val err: String) : ValidationEvent()
    }
}