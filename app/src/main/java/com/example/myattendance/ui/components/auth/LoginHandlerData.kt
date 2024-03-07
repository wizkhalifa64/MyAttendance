package com.example.myattendance.ui.components.auth

data class LoginHandlerData(
    val email: String = "",
    val emailError: String? = null,
    val location: String = "",
    val locationError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)
