package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.example.project.presentation.navigation.AppNavigation
import org.example.project.presentation.theme.GymTrackerTheme

@Composable
fun App() {
    GymTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            AppNavigation()
        }
    }
}