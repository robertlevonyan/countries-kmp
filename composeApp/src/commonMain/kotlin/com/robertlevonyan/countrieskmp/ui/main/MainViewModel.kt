package com.robertlevonyan.countrieskmp.ui.main

import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class MainViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {
    val countries = MutableStateFlow<Map<String, List<Country>>?>(null)

    init {
        viewModelScope.launch {
            countries.value = countriesRepository.getCountries()
        }
    }

    fun search(searchText: String) {
        viewModelScope.launch {
            countries.value = countriesRepository.getCountries(searchText)
        }
    }
}
