package com.robertlevonyan.countrieskmp.di

import org.koin.core.context.startKoin

fun appModules() = listOf(networkModule, repositoryModule)

fun initKoin(){
    startKoin {
        modules(appModules())
    }
}
