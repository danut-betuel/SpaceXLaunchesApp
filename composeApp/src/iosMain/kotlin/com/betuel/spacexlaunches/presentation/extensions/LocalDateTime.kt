package com.betuel.spacexlaunches.presentation.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter

actual fun LocalDateTime.format(format: String): String {
    val defValue = "error_formatting"

    return try {
        val dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = format
        dateFormatter.stringFromDate(this.toInstant(TimeZone.UTC).toNSDate())
    } catch (e: Exception) {
        defValue
    }
}