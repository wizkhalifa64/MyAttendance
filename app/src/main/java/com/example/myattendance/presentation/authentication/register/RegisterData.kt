package com.example.myattendance.presentation.authentication.register

data class RegisterData(
    val name:String = "",
    val email:String = "",
    val password:String = "",
    val location:Map<String,String> = emptyMap()
)
