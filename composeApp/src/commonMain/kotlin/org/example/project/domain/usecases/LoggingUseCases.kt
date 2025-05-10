package org.example.project.domain.usecases

import kotlinx.datetime.LocalDate
import org.example.project.domain.models.ExerciseLog
import org.example.project.domain.models.NutritionLog
import org.example.project.domain.models.WorkoutLog
import org.example.project.domain.repositorios.NutritionLogRepository
import org.example.project.domain.repositorios.WorkoutLogRepository

class LogWorkoutUseCase(private val repository: WorkoutLogRepository) {
    suspend operator fun invoke(
        workoutId: String,
        date: LocalDate,
        exerciseLogs: List<ExerciseLog>
    ): String {
        val workoutLog = WorkoutLog(
            id = "",  // Repository will generate ID
            workoutId = workoutId,
            date = date,
            exerciseLogs = exerciseLogs
        )
        return repository.createWorkoutLog(workoutLog)
    }
}

class LogNutritionUseCase(private val repository: NutritionLogRepository) {
    suspend operator fun invoke(
        profileId: String,
        date: LocalDate,
        calories: Int,
        protein: Float,
        carbs: Float,
        fat: Float
    ): String {
        val nutritionLog = NutritionLog(
            id = "",  // Repository will generate ID
            profileId = profileId,
            date = date,
            calories = calories,
            protein = protein,
            carbs = carbs,
            fat = fat
        )
        return repository.createNutritionLog(nutritionLog)
    }
}