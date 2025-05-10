package org.example.project.domain.usecases

import kotlinx.coroutines.flow.Flow
import org.example.project.domain.models.Profile
import org.example.project.domain.repositorios.ProfileRepository

class GetProfilesUseCase(private val repository: ProfileRepository) {
    operator fun invoke(): Flow<List<Profile>> = repository.getProfiles()
}

class CreateProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(name: String): String {
        val profile = Profile(
            id = "",  // Repository will generate ID
            name = name
        )
        return repository.createProfile(profile)
    }
}

class DeleteProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(id: String) = repository.deleteProfile(id)
}
