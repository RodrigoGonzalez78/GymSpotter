package org.example.project.presentation.viewModels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.example.project.domain.models.Exercise
import org.example.project.domain.models.Workout
import org.example.project.domain.repositorios.WorkoutRepository

class WorkoutViewModel(
    private val workoutRepository: WorkoutRepository
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts

    private val _selectedWorkout = MutableStateFlow<Workout?>(null)
    val selectedWorkout: StateFlow<Workout?> = _selectedWorkout

    fun loadWorkoutsByProfile(profileId: String) {
        workoutRepository.getWorkoutsByProfile(profileId)
            .onEach { workouts ->
                _workouts.value = workouts
            }
            .launchIn(viewModelScope)
    }

    fun loadWorkoutsByDay(profileId: String, dayOfWeek: Int) {
        workoutRepository.getWorkoutsByDay(profileId, dayOfWeek)
            .onEach { workouts ->
                _workouts.value = workouts
            }
            .launchIn(viewModelScope)
    }

    fun loadWorkout(id: String) {
        viewModelScope.launch {
            _selectedWorkout.value = workoutRepository.getWorkoutById(id)
        }
    }

    fun createWorkout(
        profileId: String,
        name: String,
        dayOfWeek: Int,
        exercises: List<Exercise>
    ) {
        viewModelScope.launch {
            val workout = Workout(
                id = "",
                profileId = profileId,
                name = name,
                dayOfWeek = dayOfWeek,
                exercises = exercises
            )
            workoutRepository.createWorkout(workout)
        }
    }

    fun updateWorkout(workout: Workout) {
        viewModelScope.launch {
            workoutRepository.updateWorkout(workout)
        }
    }

    fun deleteWorkout(id: String) {
        viewModelScope.launch {
            workoutRepository.deleteWorkout(id)
        }
    }
}