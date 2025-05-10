package org.example.project.domain.models

data class Workout(
    val id: String,
    val profileId: String,
    val name: String,
    val dayOfWeek: Int, // 1-7 for Monday-Sunday
    val exercises: List<Exercise>
)