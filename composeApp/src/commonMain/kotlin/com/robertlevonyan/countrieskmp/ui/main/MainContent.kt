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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.robertlevon.countrieskmp.Country
import com.robertlevonyan.countrieskmp.ui.ARG_COUNTRY
import com.robertlevonyan.countrieskmp.ui.NavigationScreens
import com.robertlevonyan.countrieskmp.ui.lottie.lottieEmptyAnimation
import com.robertlevonyan.countrieskmp.ui.lottie.lottieLoadingAnimation
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import com.robertlevonyan.countrieskmp.ui.theme.PurpleGrey40
import com.robertlevonyan.countrieskmp.ui.theme.PurpleGrey80
import com.robertlevonyan.countrieskmp.ui.theme.RoundedRectShape
import com.robertlevonyan.countrieskmp.ui.theme.ThumbSize
import com.robertlevonyan.countrieskmp.ui.util.header
import com.robertlevonyan.countrieskmp.ui.util.isTablet
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
    navigator: Navigator,
    viewModel: MainViewModel = koinViewModel(vmClass = MainViewModel::class)
) {
    val countries by viewModel.countries.collectAsState()

    AnimatedVisibility(
        visible = countries == null,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        LoadingContent()
    }

    AnimatedVisibility(
        visible = countries != null,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        val searchBackgroundColor = if (isDarkTheme) PurpleGrey40 else PurpleGrey80
        val isTablet = isTablet()

        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            contentPadding = paddingValues,
        ) {
            header {
                SearchComponent(
                    backgroundColor = searchBackgroundColor,
                    onSearchInputChange = { changedSearchText ->
                        viewModel.search(changedSearchText)
                    },
                )
            }
            header {
                AnimatedVisibility(
                    visible = countries?.isEmpty() == true,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    EmptyContent()
                }
            }
            countries?.forEach { (letter, countries) ->
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
                if (isTablet) {
                    items(countries) {
                        CountryGridItem(
                            country = it,
                            onCountryClick = { country ->
                                navigator.navigate(route = "${NavigationScreens.Details.name}?$ARG_COUNTRY=${country.cca2}")
                            },
                        )
                    }
                } else {
                    items(span = { GridItemSpan(maxLineSpan) }, items = countries) {
                        CountryListItem(
                            country = it,
                            onCountryClick = { country ->
                                navigator.navigate(route = "${NavigationScreens.Details.name}?$ARG_COUNTRY=${country.cca2}")
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
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
private fun EmptyContent() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.JsonString(jsonString = lottieEmptyAnimation)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LottieAnimation(
            modifier = Modifier.align(Alignment.BottomCenter),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}

@Composable
private fun CountryGridItem(
    country: Country,
    onCountryClick: (Country) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedRectShape)
            .clickable { onCountryClick(country) }
            .padding(HalfPadding)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ThumbSize)
                .clip(RoundedRectShape)
                .align(Alignment.CenterHorizontally),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            model = country.countryFlag,
            clipToBounds = true,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = country.commonName.orEmpty(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = country.capital.orEmpty(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun CountryListItem(
    country: Country,
    onCountryClick: (Country) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedRectShape)
            .clickable { onCountryClick(country) }
            .padding(HalfPadding)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ThumbSize)
                .clip(RoundedRectShape)
                .align(Alignment.CenterVertically),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            model = country.countryFlag,
            clipToBounds = true,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(HalfPadding)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = country.commonName.orEmpty(),
                fontWeight = FontWeight.Bold,
            )
            Text(text = country.capital.orEmpty())
        }
    }
}
