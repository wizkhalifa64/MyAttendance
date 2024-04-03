package com.example.myattendance.presentation.validation

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false, errorMessage = "Email is required"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(successful = false, errorMessage = "Invalid email")
        }
        return ValidationResult(successful = true)
    }
}