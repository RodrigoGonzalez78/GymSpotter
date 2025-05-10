package org.example.project.domain.usecases

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.models.Exercise
import org.example.project.domain.models.Workout
import org.example.project.domain.repositorios.WorkoutRepository

class GetWorkoutsByProfileUseCase(private val repository: WorkoutRepository) {
    operator fun invoke(profileId: String): Flow<List<Workout>> =
        repository.getWorkoutsByProfile(profileId)
}

class GetWorkoutsByDayUseCase(private val repository: WorkoutRepository) {
    operator fun invoke(profileId: String, dayOfWeek: Int): Flow<List<Workout>> =
        repository.getWorkoutsByDay(profileId, dayOfWeek)
}

class CreateWorkoutUseCase(private val repository: WorkoutRepository) {
    suspend operator fun invoke(
        profileId: String,
        name: String,
        dayOfWeek: Int,
        exercises: List<Exercise>
    ): String {
        val workout = Workout(
            id = "",  // Repository will generate ID
            profileId = profileId,
            name = name,
            dayOfWeek = dayOfWeek,
            exercises = exercises
        )
        return repository.createWorkout(workout)
    }
}