package com.betuel.spacexlaunches.data.di

import com.betuel.spacexlaunches.data.cache.AndroidDatabaseDriverFactory
import com.betuel.spacexlaunches.data.cache.DatabaseDriverFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val androidDataModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidApplication())}
}