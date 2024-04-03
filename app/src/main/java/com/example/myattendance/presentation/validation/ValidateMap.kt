package com.example.myattendance.presentation.validation

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