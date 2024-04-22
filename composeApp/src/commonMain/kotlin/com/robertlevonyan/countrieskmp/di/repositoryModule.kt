package com.robertlevonyan.countrieskmp.di

import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.repository.CountriesRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val repositoryModule = DI.Module("repositoryModule") {
    bindSingleton<CountriesRepository> {
        CountriesRepositoryImpl(
            httpClient = instance(),
            json = instance(),
        )
    }
}
