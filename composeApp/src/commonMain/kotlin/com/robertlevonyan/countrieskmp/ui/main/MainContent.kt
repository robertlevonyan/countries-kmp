package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.ui.theme.FabPadding
import org.koin.compose.koinInject

@Composable
fun MainContent(paddingValues: PaddingValues) {
    val countriesRepository: CountriesRepository = koinInject()
    var countries by remember { mutableStateOf(emptyList<Country>()) }
    LaunchedEffect(true) {
        countries = countriesRepository.getCountries()
    }

    var searchText by remember { mutableStateOf("") }
    LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
        item {
            SearchComponent { changedSearchText ->
                searchText = changedSearchText
            }
        }
        items(countries) { country: Country ->
            Text(
                modifier = Modifier.padding(FabPadding),
                text = country.name?.common.orEmpty(),
            )
        }
    }
}
