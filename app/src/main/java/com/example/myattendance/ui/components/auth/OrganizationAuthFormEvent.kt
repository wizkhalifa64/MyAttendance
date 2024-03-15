package com.example.myattendance.ui.components.auth

sealed class RegisterOrgFormEvent {
    data class OrganizationNameChange(val name: String) : RegisterOrgFormEvent()
    data class LocationChange(val location: Map<String, String>) : RegisterOrgFormEvent()
    data class EmailChange(val email: String) : RegisterOrgFormEvent()
    data class PasswordChange(val password: String) : RegisterOrgFormEvent()
    data object Submit : RegisterOrgFormEvent()
}

sealed class LoginOrgFormEvent {
    data class EmailChange(val email: String) : LoginOrgFormEvent()
    data class PasswordChange(val password: String) : LoginOrgFormEvent()
    data object Submit : LoginOrgFormEvent()
}