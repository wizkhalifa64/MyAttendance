package com.example.myattendance

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var appJustLaunched by mutableStateOf(true)
    var userIsAuthenticated by mutableStateOf(false)
    fun login() {
        userIsAuthenticated = true
        appJustLaunched = false
    }

    fun logout() {
        userIsAuthenticated = false
    }
}