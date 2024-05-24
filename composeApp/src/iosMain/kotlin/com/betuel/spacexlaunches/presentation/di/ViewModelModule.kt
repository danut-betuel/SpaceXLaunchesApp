package com.betuel.spacexlaunches.presentation.di

import com.betuel.spacexlaunches.presentation.pages.rocketLaunch.RocketLaunchViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule = module {
    singleOf(::RocketLaunchViewModel)
}