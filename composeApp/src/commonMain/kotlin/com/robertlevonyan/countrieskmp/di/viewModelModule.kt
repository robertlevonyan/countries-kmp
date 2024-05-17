package com.robertlevonyan.countrieskmp.di

import com.robertlevonyan.countrieskmp.ui.details.DetailsViewModel
import com.robertlevonyan.countrieskmp.ui.main.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }

    factory { DetailsViewModel(get()) }
}
