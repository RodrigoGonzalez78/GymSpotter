package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.presentation.screens.HomeScreen
import org.example.project.presentation.screens.LoggingScreen
import org.example.project.presentation.screens.NutritionScreen
import org.example.project.presentation.screens.ProfileScreen
import org.example.project.presentation.screens.WorkoutScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("profiles") {
            ProfileScreen(
                viewModel = koin,
                navController = navController
            )
        }
        composable("workouts/{profileId}") { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString ("profileId") ?: ""
            WorkoutScreen(
                profileId = profileId,
                viewModel = koinViewModel(),
                navController = navController
            )
        }
        composable("logging/{workoutId}") { backStackEntry ->
            val workoutId = backStackEntry.arguments?.getString("workoutId") ?: ""
            LoggingScreen(
                workoutId = workoutId,
                viewModel = koinViewModel(),
                navController = navController
            )
        }
        composable("nutrition/{profileId}") { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString("profileId") ?: ""
            NutritionScreen(
                profileId = profileId,
                viewModel = koinViewModel(),
                navController = navController
            )
        }
    }
}