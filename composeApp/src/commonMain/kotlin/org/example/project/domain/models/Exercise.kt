package org.example.project.domain.models

data class Exercise(
    val id: String,
    val name: String,
    val targetSets: Int,
    val targetReps: Int
)