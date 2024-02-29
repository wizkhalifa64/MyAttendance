package com.example.myattendance

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.AsyncImage


@Composable
fun LaunchList(onLaunchClick: (launchId: String) -> Unit) {
    val launchList = remember { mutableStateOf(emptyList<LaunchListQuery.Launch>()) }
    LaunchedEffect(Unit) {
        val response = apolloClient.query(LaunchListQuery()).execute()
        launchList.value = response.data?.launches?.launches?.filterNotNull() ?: emptyList()
    }
    LazyColumn(content = {
        items(launchList.value) { launch -> LaunchItem(launch = launch, onClick = {}) }
    })
}

@Composable
private fun LaunchItem(launch: LaunchListQuery.Launch, onClick: (launchId: String) -> Unit) {
    ListItem(headlineContent = { Text(text = "Launch ${launch.id}") },
        leadingContent = {
            AsyncImage(
                model = launch.mission?.missionPatch,
                contentDescription = "Mission description"
            )
        }
    )
}
