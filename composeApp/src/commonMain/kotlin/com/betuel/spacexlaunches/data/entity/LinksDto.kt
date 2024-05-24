package com.betuel.spacexlaunches.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksDto(
    @SerialName("patch")
    val patchDto: PatchDto?,
    @SerialName("article")
    val article: String?
)