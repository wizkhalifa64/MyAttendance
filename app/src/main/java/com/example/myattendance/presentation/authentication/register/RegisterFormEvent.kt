package com.example.myattendance.presentation.authentication.register

sealed class RegisterFormEvent {
    data class HandleNameChange(val name: String) : RegisterFormEvent()
    data class HandleEmailChange(val email: String) : RegisterFormEvent()
    data class HandlePasswordChange(val password: String) : RegisterFormEvent()
    data class HandleLocationChange(val location: String) : RegisterFormEvent()

}