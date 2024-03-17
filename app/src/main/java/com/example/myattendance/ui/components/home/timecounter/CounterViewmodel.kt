package com.example.myattendance.ui.components.home.timecounter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewmodel : ViewModel() {
    var counterState by mutableStateOf(TimeCounterData())
    fun startCounter() {
        counterState = counterState.copy(isRunning = true)

    }

    fun stopCounter() {
        counterState = counterState.copy(isRunning = false)
    }

    fun counter() {
        viewModelScope.launch {
            val counter = counterState.minutes
            delay(60000L)
            counterState = counterState.copy(
                minutes = counter + 1
            )
        }
    }
}