package com.example.myattendance.ui.components.home.helper

sealed class OrgDetailsFormEvent {
    data class OrgNameChange(val orgName: String) : OrgDetailsFormEvent()
    data class EmployeeCountChange(val employeeCount: String) : OrgDetailsFormEvent()
    data class StartTimeChange(val startTime: String) : OrgDetailsFormEvent()
    data class EndTimeChange(val endTime: String) : OrgDetailsFormEvent()
    object submit : OrgDetailsFormEvent()
}