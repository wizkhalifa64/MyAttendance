package com.example.myattendance.presentation.validation

class ValidateString {
    fun execute(value: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "This Field is required"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}