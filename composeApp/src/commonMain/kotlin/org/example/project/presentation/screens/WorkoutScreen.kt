package org.example.project.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gymtracker.domain.entities.Workout
import com.gymtracker.presentation.viewmodels.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    profileId: String,
    viewModel: WorkoutViewModel,
    navController: NavController
) {
    LaunchedEffect(profileId) {
        viewModel.loadWorkouts(profileId)
    }

    val workouts by viewModel.workouts.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workouts") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Workout")
                    }
                    IconButton(onClick = { navController.navigate("nutrition/$profileId") }) {
                        Icon(
                            imageVector = Icons.Default.Add, // Replace with a nutrition icon
                            contentDescription = "Nutrition Tracking"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (workouts.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No workouts yet. Create one to get started!")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(workouts) { workout ->
                        WorkoutItem(
                            workout = workout,
                            onWorkoutClick = {
                                navController.navigate("logging/${workout.id}")
                            }
                        )
                    }
                }
            }
        }
    }

    // Add workout dialog would go here
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutItem(
    workout: Workout,
    onWorkoutClick: () -> Unit
) {
    Card(
        onClick = onWorkoutClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = workout.name,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Day: ${getDayName(workout.dayOfWeek)}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${workout.exercises.size} exercises",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

fun getDayName(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Unknown"
    }
}
