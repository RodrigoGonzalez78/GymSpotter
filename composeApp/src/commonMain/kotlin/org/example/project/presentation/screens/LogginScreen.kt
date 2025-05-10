package org.example.project.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gymtracker.domain.entities.Exercise
import com.gymtracker.domain.entities.ExerciseLog
import com.gymtracker.presentation.viewmodels.LoggingViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.domain.models.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoggingScreen(
    workoutId: String,
    viewModel: LoggingViewModel,
    navController: NavController
) {
    LaunchedEffect(workoutId) {
        viewModel.loadWorkout(workoutId)
    }

    val workout by viewModel.workout.collectAsState()
    val exerciseLogs = remember { mutableStateMapOf<String, ExerciseLog>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(workout?.name ?: "Workout") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (workout != null) {
                                val today = Clock.System.now()
                                    .toLocalDateTime(TimeZone.currentSystemDefault())
                                    .date

                                viewModel.logWorkout(
                                    workoutId = workoutId,
                                    date = today,
                                    exerciseLogs = exerciseLogs.values.toList()
                                )
                                navController.navigateUp()
                            }
                        }
                    ) {
                        Icon(Icons.Default.Save, contentDescription = "Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (workout == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(workout?.exercises ?: emptyList()) { exercise ->
                        ExerciseLogItem(
                            exercise = exercise,
                            onLogUpdated = { sets, reps, weight ->
                                exerciseLogs[exercise.id] = ExerciseLog(
                                    id = "",
                                    exerciseId = exercise.id,
                                    sets = sets,
                                    reps = reps,
                                    weight = weight
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseLogItem(
    exercise: Exercise,
    onLogUpdated: (sets: Int, reps: Int, weight: Float) -> Unit
) {
    var sets by remember { mutableStateOf(exercise.targetSets.toString()) }
    var reps by remember { mutableStateOf(exercise.targetReps.toString()) }
    var weight by remember { mutableStateOf("0.0") }

    Card(
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
                text = exercise.name,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Target: ${exercise.targetSets} sets x ${exercise.targetReps} reps",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = sets,
                    onValueChange = {
                        sets = it
                        onLogUpdated(
                            sets.toIntOrNull() ?: 0,
                            reps.toIntOrNull() ?: 0,
                            weight.toFloatOrNull() ?: 0f
                        )
                    },
                    label = { Text("Sets") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    value = reps,
                    onValueChange = {
                        reps = it
                        onLogUpdated(
                            sets.toIntOrNull() ?: 0,
                            reps.toIntOrNull() ?: 0,
                            weight.toFloatOrNull() ?: 0f
                        )
                    },
                    label = { Text("Reps") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    value = weight,
                    onValueChange = {
                        weight = it
                        onLogUpdated(
                            sets.toIntOrNull() ?: 0,
                            reps.toIntOrNull() ?: 0,
                            weight.toFloatOrNull() ?: 0f
                        )
                    },
                    label = { Text("Weight") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}