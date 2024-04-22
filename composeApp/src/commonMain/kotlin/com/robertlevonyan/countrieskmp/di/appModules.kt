package com.robertlevonyan.countrieskmp.di

import org.kodein.di.DI

fun getDi() = DI.from(listOf(networkModule, repositoryModule))
