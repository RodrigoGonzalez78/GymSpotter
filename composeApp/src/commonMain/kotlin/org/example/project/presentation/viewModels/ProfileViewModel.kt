package org.example.project.presentation.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.example.project.domain.models.Profile
import org.example.project.domain.usecases.CreateProfileUseCase
import org.example.project.domain.usecases.DeleteProfileUseCase
import org.example.project.domain.usecases.GetProfilesUseCase

class ProfileViewModel(
    private val getProfilesUseCase: GetProfilesUseCase,
    private val createProfileUseCase: CreateProfileUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase
) : ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _profiles = MutableStateFlow<List<Profile>>(emptyList())
    val profiles: StateFlow<List<Profile>> = _profiles

    init {
        loadProfiles()
    }

    private fun loadProfiles() {
        getProfilesUseCase().onEach { profiles ->
            _profiles.value = profiles
        }.launchIn(viewModelScope)
    }

    fun createProfile(name: String) {
        viewModelScope.launch {
            createProfileUseCase(name)
        }
    }

    fun deleteProfile(id: String) {
        viewModelScope.launch {
            deleteProfileUseCase(id)
        }
    }
}

// Similar implementations for WorkoutViewModel and LoggingViewModel