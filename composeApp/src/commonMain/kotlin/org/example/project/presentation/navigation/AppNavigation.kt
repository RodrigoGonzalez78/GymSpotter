package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gymtracker.presentation.screens.HomeScreen
import com.gymtracker.presentation.screens.ProfileScreen
import com.gymtracker.presentation.screens.WorkoutScreen
import com.gymtracker.presentation.screens.LoggingScreen
import com.gymtracker.presentation.screens.NutritionScreen
import moe.tlaster.precompose.navigation.NavHost
import org.koin.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("profiles") {
            ProfileScreen(
                viewModel = koinViewModel(),
                navController = navController
            )
        }
        composable("workouts/{profileId}") { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString("profileId") ?: ""
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