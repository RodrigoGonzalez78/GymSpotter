package org.example.project.data.datasources

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.models.Profile

interface ProfileDataSource {
    fun getProfiles(): Flow<List<Profile>>
    suspend fun getProfileById(id: String): Profile?
    suspend fun saveProfile(profile: Profile)
    suspend fun deleteProfile(id: String)
}