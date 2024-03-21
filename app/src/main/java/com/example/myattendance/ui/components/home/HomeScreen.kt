package com.example.myattendance.ui.components.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myattendance.ui.components.home.timecounter.TimeCounter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TimeCounter()
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .weight(0.5f)
                        .padding(12.dp)
                ) {
                    Text(text = "Leave\r\nBalance", style = MaterialTheme.typography.titleMedium)
                    Text(text = "20", style = MaterialTheme.typography.headlineMedium)
                }
                Column(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .weight(0.5f)
                        .background(MaterialTheme.colorScheme.onPrimary)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Leave\r\nApproved", style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = "20", style = MaterialTheme.typography.headlineMedium)

                    }
                }
            }
//            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
//                Text(
//                    text = "Looks like you have not created Organization",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//            Row(
//                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
//            ) {
//                Button(onClick = { navController.navigate("createOrgDetails") }) {
//                    Text(text = "Lets Create")
//                }
//            }
        }
    }
}