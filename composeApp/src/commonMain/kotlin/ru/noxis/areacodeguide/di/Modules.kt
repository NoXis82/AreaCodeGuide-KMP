package ru.noxis.areacodeguide.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.noxis.areacodeguide.data.database.DatabaseFactory
import ru.noxis.areacodeguide.data.database.RegionsDatabase
import ru.noxis.areacodeguide.data.repository.DefaultRegionRepository
import ru.noxis.areacodeguide.domain.RegionsRepository
import ru.noxis.areacodeguide.presentation.viewmodel.RegionViewModel
import ru.noxis.areacodeguide.presentation.viewmodel.SplashViewModel

expect val platformModule: Module

val sharedModule = module {
    singleOf(::DefaultRegionRepository).bind<RegionsRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<RegionsDatabase>().regionDao }

    viewModelOf(::RegionViewModel)
    viewModelOf(::SplashViewModel)
}