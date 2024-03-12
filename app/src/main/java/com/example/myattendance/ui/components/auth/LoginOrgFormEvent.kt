package com.example.myattendance.ui.components.auth

sealed class LoginOrgFormEvent {
    data class OrganizationNameChange(val name: String) : LoginOrgFormEvent()
    data class LocationChange(val location: Map<String, String>) : LoginOrgFormEvent()
    data class EmailChange(val email: String) : LoginOrgFormEvent()
    data class PasswordChange(val password: String) : LoginOrgFormEvent()
    data class Submit(val type: String) : LoginOrgFormEvent()
}