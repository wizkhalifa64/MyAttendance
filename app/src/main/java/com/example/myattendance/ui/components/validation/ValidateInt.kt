package com.example.myattendance.ui.components.validation

import android.util.Patterns

class ValidateInt {
    fun execute(value: Int): ValidationResult {
        return ValidationResult(successful = true)
    }
}