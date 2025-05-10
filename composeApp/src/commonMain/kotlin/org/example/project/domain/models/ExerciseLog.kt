package org.example.project.domain.models

data class ExerciseLog(
    val id: String,
    val exerciseId: String,
    val sets: Int,
    val reps: Int,
    val weight: Float
)