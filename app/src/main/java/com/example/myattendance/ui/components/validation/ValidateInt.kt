package com.example.myattendance.ui.components.validation

import com.example.myattendance.presentation.validation.ValidationResult

class ValidateInt {
    fun execute(value: Int): ValidationResult {
        return ValidationResult(successful = true)
    }
}