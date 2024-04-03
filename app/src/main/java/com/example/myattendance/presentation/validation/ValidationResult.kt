package com.example.myattendance.presentation.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
