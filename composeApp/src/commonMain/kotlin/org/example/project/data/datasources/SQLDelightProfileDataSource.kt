package org.example.project.data.datasources


import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.domain.models.Profile

class SQLDelightProfileDataSource(
    private val database: Database
) : ProfileDataSource {

    override fun getProfiles(): Flow<List<Profile>> =
        database.profileQueries.getProfiles().asFlow().mapToList().map { profiles ->
            profiles.map { it.toProfile() }
        }

    override suspend fun getProfileById(id: String): Profile? =
        database.profileQueries.getProfileById(id).executeAsOneOrNull()?.toProfile()

    override suspend fun saveProfile(profile: Profile) {
        database.profileQueries.insertProfile(
            id = profile.id,
            name = profile.name
        )
    }

    override suspend fun deleteProfile(id: String) {
        database.profileQueries.deleteProfile(id)
    }

    private fun ProfileEntity.toProfile(): Profile =
        Profile(
            id = id,
            name = name
        )
}

// Similar implementations for other data sources