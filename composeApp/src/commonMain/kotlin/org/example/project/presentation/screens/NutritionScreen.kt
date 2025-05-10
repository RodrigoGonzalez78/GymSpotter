package org.example.project.presentation.screens


import androidx.compose.foundation.layout.*
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
import com.gymtracker.presentation.viewmodels.NutritionViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen(
    profileId: String,
    viewModel: NutritionViewModel,
    navController: NavController
) {
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    LaunchedEffect(profileId, today) {
        viewModel.loadNutritionLog(profileId, today)
    }

    val nutritionLog by viewModel.nutritionLog.collectAsState()

    var calories by remember { mutableStateOf(nutritionLog?.calories?.toString() ?: "0") }
    var protein by remember { mutableStateOf(nutritionLog?.protein?.toString() ?: "0.0") }
    var carbs by remember { mutableStateOf(nutritionLog?.carbs?.toString() ?: "0.0") }
    var fat by remember { mutableStateOf(nutritionLog?.fat?.toString() ?: "0.0") }

    LaunchedEffect(nutritionLog) {
        nutritionLog?.let {
            calories = it.calories.toString()
            protein = it.protein.toString()
            carbs = it.carbs.toString()
            fat = it.fat.toString()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nutrition Tracking") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.saveNutritionLog(
                                profileId = profileId,
                                date = today,
                                calories = calories.toIntOrNull() ?: 0,
                                protein = protein.toFloatOrNull() ?: 0f,
                                carbs = carbs.toFloatOrNull() ?: 0f,
                                fat = fat.toFloatOrNull() ?: 0f
                            )
                            navController.navigateUp()
                        }
                    ) {
                        Icon(Icons.Default.Save, contentDescription = "Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today's Nutrition",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            NutritionInput(
                label = "Calories",
                value = calories,
                onValueChange = { calories = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NutritionInput(
                label = "Protein (g)",
                value = protein,
                onValueChange = { protein = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NutritionInput(
                label = "Carbs (g)",
                value = carbs,
                onValueChange = { carbs = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            NutritionInput(
                label = "Fat (g)",
                value = fat,
                onValueChange = { fat = it }
            )
        }
    }
}

@Composable
fun NutritionInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = Modifier.fillMaxWidth()
    )
}