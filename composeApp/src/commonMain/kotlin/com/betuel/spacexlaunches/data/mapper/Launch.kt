package com.betuel.spacexlaunches.data.mapper

import com.betuel.spacexlaunches.data.entity.LinksDto
import com.betuel.spacexlaunches.data.entity.PatchDto
import com.betuel.spacexlaunches.data.entity.RocketLaunchDto
import com.betuel.spacexlaunches.domain.entity.Links
import com.betuel.spacexlaunches.domain.entity.Patch
import com.betuel.spacexlaunches.domain.entity.RocketLaunch

fun RocketLaunchDto.toRocketLaunch(): RocketLaunch {
    return RocketLaunch(
        flightNumber = flightNumber,
        missionName = missionName,
        launchDateUTC = launchDateUTC,
        details = details,
        launchSuccess = launchSuccess,
        links = linksDto.toLinks()
    )
}

fun LinksDto.toLinks(): Links {
    return Links(
        patch = patchDto?.toPatch(),
        article = article
    )
}

fun PatchDto?.toPatch(): Patch? {
    if (this == null) return null

    return Patch(
        small = small,
        large = large
    )
}