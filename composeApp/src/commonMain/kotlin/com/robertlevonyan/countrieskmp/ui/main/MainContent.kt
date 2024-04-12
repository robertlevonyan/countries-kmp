@file:OptIn(ExperimentalFoundationApi::class)

package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import com.robertlevonyan.countrieskmp.ui.theme.RoundedRectShape
import com.robertlevonyan.countrieskmp.ui.theme.ThumbSize
import org.koin.compose.koinInject

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
) {
    val countriesRepository: CountriesRepository = koinInject()
    var countries by remember { mutableStateOf(emptyMap<String, List<Country>>()) }
    LaunchedEffect(true) {
        countries = countriesRepository.getCountries()
    }

    var searchText by remember { mutableStateOf("") }
    LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
        item {
            SearchComponent(isDarkTheme) { changedSearchText ->
                searchText = changedSearchText
            }
        }
        countries.forEach { (letter, countries) ->
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                        .padding(HalfPadding)
                ) {
                    Text(
                        text = letter,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            items(countries) { country: Country ->
                CountryItem(country)
            }
        }
    }
}

@Composable
fun CountryItem(country: Country) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {

            }
            .padding(HalfPadding)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ThumbSize)
                .clip(RoundedRectShape)
                .align(Alignment.CenterVertically),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            model = country.flags?.getOrElse("png") { "" },
            clipToBounds = true,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(HalfPadding)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = country.name?.common.orEmpty(),
                fontWeight = FontWeight.Bold,
            )
            Text(text = country.capital?.firstOrNull().orEmpty())
        }
    }
}