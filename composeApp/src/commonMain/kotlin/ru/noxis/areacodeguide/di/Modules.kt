package ru.noxis.areacodeguide.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.noxis.areacodeguide.data.database.DatabaseFactory
import ru.noxis.areacodeguide.data.database.RegionsDatabase

expect val platformModule: Module

val sharedModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<RegionsDatabase>().regionDao }
}