package com.robertlevonyan.countrieskmp.di

import app.cash.sqldelight.db.SqlDriver
import com.robertlevon.countrieskmp.AppDatabase
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.repository.CountriesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { AppDatabase(get<DatabaseDriverFactory>().provideDbDriver()) }

    single { get<AppDatabase>().countriesQueries }

    single<CountriesRepository> {
        CountriesRepositoryImpl(
            httpClient = get(),
            json = get(),
            countriesQueries = get(),
        )
    }
}

expect class DatabaseDriverFactory() {
    fun provideDbDriver(): SqlDriver
}
