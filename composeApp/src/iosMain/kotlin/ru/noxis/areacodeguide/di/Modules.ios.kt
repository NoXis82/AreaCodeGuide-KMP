package ru.noxis.areacodeguide.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.noxis.areacodeguide.data.database.DatabaseFactory

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory() }
    }