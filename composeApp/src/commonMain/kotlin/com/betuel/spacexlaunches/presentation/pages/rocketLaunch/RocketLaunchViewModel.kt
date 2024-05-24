package com.betuel.spacexlaunches.presentation.pages.rocketLaunch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betuel.spacexlaunches.domain.repository.ILaunchesRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RocketLaunchViewModel(
    private val launchesRepository: ILaunchesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RocketLaunchScreenState())
    val state = _state.asStateFlow()

    fun loadLaunches() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, launches = emptyList())
            }
            try {
                val launches = launchesRepository.getLaunches(forceReload = true)
                _state.update { state ->
                    state.copy(isLoading = false, launches = launches.sortedByDescending { it.flightNumber })
                }
            } catch (e: Exception) {
                println(e)
                _state.update {
                    it.copy(isLoading = false, launches = emptyList())
                }
            }
        }
    }

    init {
        loadLaunches()
    }
}