package org.example.project.domain.repositorios

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.models.Workout

interface WorkoutRepository {
    fun getWorkoutsByProfile(profileId: String): Flow<List<Workout>>
    fun getWorkoutsByDay(profileId: String, dayOfWeek: Int): Flow<List<Workout>>
    suspend fun getWorkoutById(id: String): Workout?
    suspend fun createWorkout(workout: Workout): String
    suspend fun updateWorkout(workout: Workout)
    suspend fun deleteWorkout(id: String)
}