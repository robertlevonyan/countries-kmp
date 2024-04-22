@file:OptIn(ExperimentalFoundationApi::class)

package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.repository.CountriesRepository
import com.robertlevonyan.countrieskmp.ui.lottie.lottieLoadingAnimation
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import com.robertlevonyan.countrieskmp.ui.theme.RoundedRectShape
import com.robertlevonyan.countrieskmp.ui.theme.ThumbSize
import com.robertlevonyan.countrieskmp.ui.util.header
import com.robertlevonyan.countrieskmp.ui.util.isTablet
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import kotlinx.coroutines.delay
import org.kodein.di.compose.localDI
import org.kodein.di.instance

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
) {
    val di = localDI()
    val countriesRepository: CountriesRepository by di.instance()
    var countries by remember { mutableStateOf(emptyMap<String, List<Country>>()) }
    LaunchedEffect(true) {
        delay(2000)
        countries = countriesRepository.getCountries()
    }

    println(countries)

    AnimatedVisibility(
        visible = countries.isEmpty(),
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        LoadingContent()
    }
    AnimatedVisibility(
        visible = countries.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        if (isTablet()) {
            CountriesGridContent(paddingValues, isDarkTheme, countries)
        } else {
            CountriesListContent(paddingValues, isDarkTheme, countries)
        }
    }
}

@Composable
fun LoadingContent() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.JsonString(jsonString = lottieLoadingAnimation)
    )

    LottieAnimation(
        modifier = Modifier.fillMaxSize(),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Composable
fun CountriesGridContent(
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
    countries: Map<String, List<Country>>,
) {
    var searchText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        SearchComponent(isDarkTheme) { changedSearchText ->
            searchText = changedSearchText
        }

        LazyVerticalGrid(columns = GridCells.Adaptive(200.dp)) {
            countries.forEach { (letter, countries) ->
                header {
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
                    CountryGridItem(country)
                }
            }
        }
    }
}

@Composable
fun CountryGridItem(country: Country) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedRectShape)
            .clickable {

            }
            .padding(HalfPadding)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ThumbSize)
                .clip(RoundedRectShape)
                .align(Alignment.CenterHorizontally),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            model = country.flags?.getOrElse("png") { "" },
            clipToBounds = true,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = country.name?.common.orEmpty(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = country.capital?.firstOrNull().orEmpty(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun CountriesListContent(
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
    countries: Map<String, List<Country>>
) {
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
                CountryListItem(country)
            }
        }
    }
}

@Composable
fun CountryListItem(country: Country) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedRectShape)
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
