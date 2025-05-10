package org.example.project.domain.repositorios

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import org.example.project.domain.models.WorkoutLog

interface WorkoutLogRepository {
    fun getWorkoutLogsByProfile(profileId: String): Flow<List<WorkoutLog>>
    fun getWorkoutLogsByDate(profileId: String, date: LocalDate): Flow<List<WorkoutLog>>
    suspend fun createWorkoutLog(workoutLog: WorkoutLog): String
    suspend fun updateWorkoutLog(workoutLog: WorkoutLog)
    suspend fun deleteWorkoutLog(id: String)
}
