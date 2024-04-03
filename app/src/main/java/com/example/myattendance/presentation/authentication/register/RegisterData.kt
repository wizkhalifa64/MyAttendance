package com.example.myattendance.presentation.authentication.register

data class RegisterData(
    val email: String = "",
    val emailError: String? = null,
    val location: Map<String, String> = emptyMap(),
    val locationError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)
