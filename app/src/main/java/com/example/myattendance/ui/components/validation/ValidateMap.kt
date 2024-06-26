package com.example.myattendance.ui.components.validation

import com.example.myattendance.presentation.validation.ValidationResult

class ValidateMap {
    fun execute(data:Any): ValidationResult {
        return if (data is Map<*,*> && data.isNotEmpty()) {
            ValidationResult(successful = true)
        }else{
            ValidationResult(
                successful = false, errorMessage = "Invalid value"
            )
        }

    }
}