package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import org.jetbrains.compose.resources.painterResource
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
    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().clickable {

    }.padding(HalfPadding)) {
//        Image(painter = painterResource())
//        coil3.Image(
//            modifier = Modifier.padding(FabPadding),
//            url = country.flag.orEmpty(),
//        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(HalfPadding)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier,
                text = country.name?.common.orEmpty(),
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier,
                text = country.capital?.firstOrNull().orEmpty(),
            )
        }
    }
}