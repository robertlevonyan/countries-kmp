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
    val regions = MutableStateFlow<List<String>>(emptyList())

    init {
        search()
        viewModelScope.launch {
            regions.value = countriesRepository.getRegions()
        }
    }

    fun search(
        searchText: String = "",
        region: String = "",
    ) {
        viewModelScope.launch {
            countriesRepository.getCountries(searchText, region).onEach {
                countries.value = it
            }.launchIn(viewModelScope)
        }
    }


}
