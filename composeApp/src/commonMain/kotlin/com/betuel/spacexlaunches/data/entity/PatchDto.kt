package com.betuel.spacexlaunches.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatchDto(
    @SerialName("small")
    val small: String?,
    @SerialName("large")
    val large: String?
)