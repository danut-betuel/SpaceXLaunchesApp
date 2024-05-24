package com.betuel.spacexlaunches.data.cache

import com.betuel.spacexlaunches.cache.AppDatabase
import com.betuel.spacexlaunches.data.entity.LinksDto
import com.betuel.spacexlaunches.data.entity.PatchDto
import com.betuel.spacexlaunches.data.entity.RocketLaunchDto

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllLaunches(): List<RocketLaunchDto> {
        return dbQuery.selectAllLaunchesInfo(::mapLaunchSelecting).executeAsList()
    }

    internal suspend fun clearAndCreateLaunches(launches: List<RocketLaunchDto>) {
        dbQuery.transaction {
            dbQuery.removeAllLaunches()
            launches.forEach { launch ->
                dbQuery.insertLaunch(
                    flightNumber = launch.flightNumber.toLong(),
                    missionName = launch.missionName,
                    details = launch.details,
                    launchSuccess = if(launch.launchSuccess == true) 1 else 0L,
                    launchDateUTC = launch.launchDateUTC,
                    patchUrlSmall = launch.linksDto.patchDto?.small,
                    patchUrlLarge = launch.linksDto.patchDto?.large,
                    articleUrl = launch.linksDto.article
                )
            }
        }
    }

    private fun mapLaunchSelecting(
        flightNumber: Long,
        missionName: String,
        details: String?,
        launchSuccess: Long?,
        launchDateUTC: String,
        patchUrlSmall: String?,
        patchUrlLarge: String?,
        articleUrl: String?
    ): RocketLaunchDto {
        return RocketLaunchDto(
            flightNumber = flightNumber.toInt(),
            missionName = missionName,
            details = details,
            launchDateUTC = launchDateUTC,
            launchSuccess = launchSuccess == 1L,
            linksDto = LinksDto(
                patchDto = PatchDto(
                    small = patchUrlSmall,
                    large = patchUrlLarge
                ),
                article = articleUrl
            )
        )
    }
}