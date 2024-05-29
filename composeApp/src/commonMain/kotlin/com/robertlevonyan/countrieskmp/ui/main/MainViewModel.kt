package com.robertlevonyan.countrieskmp.ui.main

import com.robertlevon.countrieskmp.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MainViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {
    val countries = MutableStateFlow<Map<String, List<Country>>?>(null)

    init {
        search()
    }

    fun search(searchText: String = "") {
        println("Searching for $searchText")
        viewModelScope.launch {
            countriesRepository.getCountries(searchText).onEach {
                countries.value = it
            }.launchIn(viewModelScope)
        }
    }
}
