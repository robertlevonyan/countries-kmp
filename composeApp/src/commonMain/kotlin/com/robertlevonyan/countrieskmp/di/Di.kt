package com.robertlevonyan.countrieskmp.di

import org.koin.dsl.module

fun getDiModules() = listOf(networkModule, repositoryModule, viewModelModule)

val sqliteModule = module {
    single {
        DatabaseDriverFactory()
    }
}
