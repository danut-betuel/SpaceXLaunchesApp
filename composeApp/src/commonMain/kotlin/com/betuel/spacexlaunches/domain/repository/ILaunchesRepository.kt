package com.betuel.spacexlaunches.domain.repository

import com.betuel.spacexlaunches.domain.entity.RocketLaunch

interface ILaunchesRepository {
    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean) : List<RocketLaunch>
}