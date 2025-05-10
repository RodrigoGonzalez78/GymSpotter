package org.example.project.data.repositores

import com.android.identity.util.UUID
import kotlinx.coroutines.flow.Flow
import org.example.project.data.datasources.ProfileDataSource
import org.example.project.domain.models.Profile
import org.example.project.domain.repositorios.ProfileRepository

class ProfileRepositoryImpl(
    private val localDataSource: ProfileDataSource
) : ProfileRepository {

    override fun getProfiles(): Flow<List<Profile>> =
        localDataSource.getProfiles()

    override suspend fun getProfileById(id: String): Profile? =
        localDataSource.getProfileById(id)

    override suspend fun createProfile(profile: Profile): String {
        val profileWithId = profile.copy(id = UUID.randomUUID().toString())
        localDataSource.saveProfile(profileWithId)
        return profileWithId.id
    }

    override suspend fun updateProfile(profile: Profile) {
        localDataSource.saveProfile(profile)
    }

    override suspend fun deleteProfile(id: String) {
        localDataSource.deleteProfile(id)
    }
}

// Similar implementations for WorkoutRepositoryImpl, WorkoutLogRepositoryImpl, and NutritionLogRepositoryImpl