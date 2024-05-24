package com.betuel.spacexlaunches.data.di

import com.betuel.spacexlaunches.data.cache.DatabaseDriverFactory
import com.betuel.spacexlaunches.data.cache.DesktopDatabaseDriverFactory
import org.koin.dsl.module

val desktopDataModule = module {
    single<DatabaseDriverFactory> { DesktopDatabaseDriverFactory() }
}