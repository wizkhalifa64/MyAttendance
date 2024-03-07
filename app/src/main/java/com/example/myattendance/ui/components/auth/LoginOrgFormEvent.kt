package com.example.myattendance.ui.components.auth

import com.example.myattendance.ui.components.home.helper.OrgDetailsFormEvent

sealed class LoginOrgFormEvent {
    data class organizationNameChange(val name: String) : LoginOrgFormEvent()
    data class locationChange(val location: String) : LoginOrgFormEvent()
    data class emailChange(val email: String) : LoginOrgFormEvent()
    data class passwordChange(val password: String) : LoginOrgFormEvent()
    object submit : LoginOrgFormEvent()
}