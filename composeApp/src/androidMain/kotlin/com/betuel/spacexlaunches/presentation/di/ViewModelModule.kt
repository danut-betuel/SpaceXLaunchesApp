package com.betuel.spacexlaunches.presentation.di

import com.betuel.spacexlaunches.presentation.pages.rocketLaunch.RocketLaunchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModelOf(::RocketLaunchViewModel)
}