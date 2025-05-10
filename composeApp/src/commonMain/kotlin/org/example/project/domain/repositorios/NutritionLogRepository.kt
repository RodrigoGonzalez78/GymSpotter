package org.example.project.domain.repositorios

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import org.example.project.domain.models.NutritionLog

interface NutritionLogRepository {
    fun getNutritionLogsByProfile(profileId: String): Flow<List<NutritionLog>>
    fun getNutritionLogByDate(profileId: String, date: LocalDate): Flow<NutritionLog?>
    suspend fun createNutritionLog(nutritionLog: NutritionLog): String
    suspend fun updateNutritionLog(nutritionLog: NutritionLog)
    suspend fun deleteNutritionLog(id: String)
}