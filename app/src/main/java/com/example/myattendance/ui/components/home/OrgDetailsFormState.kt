package com.example.myattendance.ui.components.home

data class OrgDetailsFormState(
    val orgName: String = "",
    val orgNameError: String? = null,
    val employeeCount: String = "",
    val employeeError: String? = null,
    val startTime: String = "",
    val startTimeError: String? = null,
    val endTime: String = "",
    val endTimeError: String? = null,
    )
