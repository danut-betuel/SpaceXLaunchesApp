package com.betuel.spacexlaunches.data.repository

import com.betuel.spacexlaunches.data.cache.Database
import com.betuel.spacexlaunches.data.cache.DatabaseDriverFactory
import com.betuel.spacexlaunches.data.mapper.toRocketLaunch
import com.betuel.spacexlaunches.data.network.SpaceXApi
import com.betuel.spacexlaunches.domain.entity.RocketLaunch
import com.betuel.spacexlaunches.domain.repository.ILaunchesRepository

class LaunchesRepository(
    databaseDriverFactory: DatabaseDriverFactory,
    private val api: SpaceXApi
) : ILaunchesRepository {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    override suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches.map { it.toRocketLaunch() }
        } else {
            api.getAllLaunches().also {
                database.clearAndCreateLaunches(it)
            }.map { it.toRocketLaunch() }
        }
    }
}