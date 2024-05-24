package com.betuel.spacexlaunches.data.di

import com.betuel.spacexlaunches.data.network.SpaceXApi
import com.betuel.spacexlaunches.data.repository.LaunchesRepository
import com.betuel.spacexlaunches.domain.repository.ILaunchesRepository
import org.koin.dsl.module

val dataModule = module {
    single { SpaceXApi() }

    single<ILaunchesRepository> { LaunchesRepository(get(), get()) }
}