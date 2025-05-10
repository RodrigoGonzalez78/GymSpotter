package org.example.project.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gym Progress Tracker",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("profiles") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Manage Profiles")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // This would typically navigate to the profile of the last used profile
        // For simplicity, we're just showing the button
        Button(
            onClick = { /* Navigate to last used profile */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Quick Start")
        }
    }
}