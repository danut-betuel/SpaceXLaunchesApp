package com.betuel.spacexlaunches.presentation.pages.rocketLaunch

import com.betuel.spacexlaunches.domain.entity.RocketLaunch

data class RocketLaunchScreenState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList()
)