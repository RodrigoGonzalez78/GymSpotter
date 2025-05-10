package org.example.project.domain.repositorios

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.models.Profile

interface ProfileRepository {
    fun getProfiles(): Flow<List<Profile>>
    suspend fun getProfileById(id: String): Profile?
    suspend fun createProfile(profile: Profile): String
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile(id: String)
}
