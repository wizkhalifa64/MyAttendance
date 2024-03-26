package com.example.myattendance.presentation.authentication.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel(

) {
    var registerState by mutableStateOf(RegisterData())
    fun handleChange(event: RegisterFormEvent){
        when(event){
            is RegisterFormEvent.HandleEmailChange -> TODO()
            is RegisterFormEvent.HandleLocationChange -> TODO()
            is RegisterFormEvent.HandleNameChange -> TODO()
            is RegisterFormEvent.HandlePasswordChange -> TODO()
        }
    }
    fun errorHandler(){
        
    }
}