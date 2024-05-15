package com.robertlevonyan.countrieskmp.di

import com.robertlevonyan.countrieskmp.ui.main.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}
