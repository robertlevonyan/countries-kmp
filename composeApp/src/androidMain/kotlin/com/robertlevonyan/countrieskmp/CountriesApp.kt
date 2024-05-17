package com.robertlevonyan.countrieskmp

import android.app.Application
import com.robertlevonyan.countrieskmp.di.DatabaseDriverFactory
import com.robertlevonyan.countrieskmp.di.getDiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CountriesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CountriesApp)
            modules(getDiModules() + module { single { DatabaseDriverFactory(get()) } })
        }
    }
}
