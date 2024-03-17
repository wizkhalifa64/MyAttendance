package com.example.myattendance.ui.components.home.timecounter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TimeCounter() {
    val viewModel = viewModel<CounterViewmodel>()
    var alert by remember {
        mutableStateOf(false)
    }

    val state = viewModel.counterState
    LaunchedEffect(key1 = state.isRunning, key2 = state.minutes) {
        if (state.isRunning) {
            viewModel.counter()
        }
    }
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Text(text = "Working Hours", style = MaterialTheme.typography.labelLarge)
                Text(text = "34H 45M", style = MaterialTheme.typography.headlineSmall)
            }
            Column {
                Text(text = "On-time arrival", style = MaterialTheme.typography.labelLarge)
                Text(text = "76%", style = MaterialTheme.typography.headlineSmall)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${state.minutes / 60}H ${state.minutes.rem(60)}M",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(onClick = {
                if (state.isRunning.not()) {
                    viewModel.startCounter()
                } else {
                    alert = true
                }
            }) {
                Text(text = if (state.isRunning) "Clock-out" else "Clock-in")
            }

        }
        if (alert) {
            ClockOutDialog(
                onDismissRequest = { alert = false },
                onConfirmation = {
                    viewModel.stopCounter()
                    alert = false
                },
                dialogTitle = "Are you want to log out?",
                dialogText = "You will be logged out after conformation",
                icon = Icons.Rounded.Info
            )
        }

    }
}