package ru.noxis.areacodeguide

import android.app.Application
import org.koin.android.ext.koin.androidContext
import ru.noxis.areacodeguide.di.initKoin

class RegionsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@RegionsApplication)
        }
    }
}