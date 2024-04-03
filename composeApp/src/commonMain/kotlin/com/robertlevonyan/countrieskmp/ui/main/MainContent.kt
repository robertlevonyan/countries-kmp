package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.Image
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.ui.theme.FabPadding
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
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
            CountryItem(country)
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    Row(
        modifier = Modifier
            .padding(HalfPadding)
            .fillMaxWidth()
            .size(70.dp)
            .aspectRatio(1f)
    ) {
//        coil3.Image(
//            modifier = Modifier.padding(FabPadding),
//            url = country.flag.orEmpty(),
//        )
        coil3.Image
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                modifier = Modifier.padding(FabPadding),
                text = country.name?.common.orEmpty(),
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(FabPadding),
                text = country.capital?.firstOrNull().orEmpty(),
            )
        }
    }
}