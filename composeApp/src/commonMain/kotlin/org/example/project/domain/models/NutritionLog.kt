package org.example.project.domain.models

import kotlinx.datetime.LocalDate

data class NutritionLog(
    val id: String,
    val profileId: String,
    val date: LocalDate,
    val calories: Int,
    val protein: Float, // in grams
    val carbs: Float,   // in grams
    val fat: Float      // in grams
)