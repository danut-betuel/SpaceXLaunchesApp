package com.betuel.spacexlaunches.data.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.betuel.spacexlaunches.cache.AppDatabase
import kotlinx.coroutines.runBlocking

class DesktopDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return runBlocking {
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
                AppDatabase.Schema.create(it).await()
            }
        }
    }
}