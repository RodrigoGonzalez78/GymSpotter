package org.example.project.domain.models
import kotlinx.datetime.LocalDate
data class WorkoutLog(
    val id: String,
    val workoutId: String,
    val date: LocalDate,
    val exerciseLogs: List<ExerciseLog>
)
