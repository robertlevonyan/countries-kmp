package com.robertlevonyan.countrieskmp.di

import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.repository.CountriesRepositoryImpl
import org.koin.dsl.module

//val repositoryModule = DI.Module("repositoryModule") {
val repositoryModule = module {
    single<CountriesRepository> {
        CountriesRepositoryImpl(
            httpClient = get(),
            json = get(),
        )
    }
}
