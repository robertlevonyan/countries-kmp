package com.robertlevonyan.countrieskmp.ui.details

import com.robertlevon.countrieskmp.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class DetailsViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {

    val currentCountry: MutableStateFlow<Country?> = MutableStateFlow(null)

    fun getCountry(cca2: String) {
        viewModelScope.launch {
            currentCountry.value = countriesRepository.getCountry(cca2)
        }
    }

}