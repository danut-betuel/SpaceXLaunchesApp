package com.betuel.spacexlaunches.domain.entity

data class RocketLaunch(
    val flightNumber: Int,
    val missionName: String,
    val launchDateUTC: String,
    val details: String?,
    val launchSuccess: Boolean?,
    val links: Links
)