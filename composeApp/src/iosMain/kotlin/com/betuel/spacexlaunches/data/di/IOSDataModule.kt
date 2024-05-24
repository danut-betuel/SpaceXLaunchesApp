package com.betuel.spacexlaunches.data.di

import com.betuel.spacexlaunches.data.cache.DatabaseDriverFactory
import com.betuel.spacexlaunches.data.cache.IOSDatabaseDriverFactory
import org.koin.dsl.module

val iOSDataModule = module {
    single<DatabaseDriverFactory> { IOSDatabaseDriverFactory() }
}