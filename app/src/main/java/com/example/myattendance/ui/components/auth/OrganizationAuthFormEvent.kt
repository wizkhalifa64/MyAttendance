package com.example.myattendance.ui.components.auth

sealed class RegisterOrgFormEvent {
    data class OrganizationNameChange(val name: String) : RegisterOrgFormEvent()
    data class LocationChange(val location: Map<String, String>) : RegisterOrgFormEvent()
    data class EmailChange(val email: String) : RegisterOrgFormEvent()
    data class PasswordChange(val password: String) : RegisterOrgFormEvent()
    data object Submit : RegisterOrgFormEvent()
}

sealed class LoginFormEvent {
    data class EmailChange(val email: String) : LoginFormEvent()
    data class PasswordChange(val password: String) : LoginFormEvent()
    data object OrganizationLogin : LoginFormEvent()
    data object EmployeeLogin:LoginFormEvent()
}