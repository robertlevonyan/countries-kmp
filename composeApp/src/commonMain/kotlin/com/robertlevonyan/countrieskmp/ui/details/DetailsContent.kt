@file:OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)

package com.robertlevonyan.countrieskmp.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.robertlevon.countrieskmp.Country
import com.robertlevonyan.countrieskmp.ui.theme.FabPadding
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import com.robertlevonyan.countrieskmp.ui.theme.RoundedRectShape
import com.robertlevonyan.countrieskmp.ui.util.isTablet
import countries_kmp.composeapp.generated.resources.Res
import countries_kmp.composeapp.generated.resources.country_area
import countries_kmp.composeapp.generated.resources.country_capital
import countries_kmp.composeapp.generated.resources.country_currency
import countries_kmp.composeapp.generated.resources.country_languages
import countries_kmp.composeapp.generated.resources.country_population
import countries_kmp.composeapp.generated.resources.country_region
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailsContent(
    paddingValues: PaddingValues,
    country: Country?,
) = country?.run {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        item {
            Card(
                modifier = Modifier
                    .padding(FabPadding)
                    .fillMaxWidth(),
                shape = RoundedRectShape,
            ) {
                DetailsHeader(country = this@run)
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(FabPadding)
                    .fillMaxWidth(),
                shape = RoundedRectShape,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(FabPadding)
                ) {
                    population?.let { population ->
                        DetailItem(
                            title = stringResource(Res.string.country_population),
                            value = population.toString(),
                        )
                    }
                    region?.let { region ->
                        DetailItem(
                            title = stringResource(Res.string.country_region),
                            value = region,
                        )
                    }
                    capital?.let { capital ->
                        DetailItem(
                            title = stringResource(Res.string.country_capital),
                            value = capital,
                        )
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.size(HalfPadding)) }
        item {
            Card(
                modifier = Modifier
                    .padding(FabPadding)
                    .fillMaxWidth(),
                shape = RoundedRectShape,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(FabPadding)
                ) {
                    area?.let { area ->
                        DetailItem(
                            title = stringResource(Res.string.country_area),
                            value = "$area km²",
                        )
                    }
                    currencies?.let { currencies ->
                        DetailItem(
                            title = stringResource(Res.string.country_currency),
                            value = currencies,
                        )
                    }
                }
            }
        }
        languages?.let { languages ->
            item { Spacer(modifier = Modifier.size(HalfPadding)) }
            item {
                Card(
                    modifier = Modifier
                        .padding(FabPadding)
                        .fillMaxWidth(),
                    shape = RoundedRectShape,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(FabPadding)
                    ) {
                        DetailItem(
                            title = stringResource(Res.string.country_languages),
                            value = languages,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailsHeader(country: Country?) {
    if (country == null) return
    val headerData = listOf(country.countryFlag, country.coatOfArms)

    if (isTablet()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyRow(modifier = Modifier.align(Alignment.Center)) {
                items(headerData) {
                    AsyncImage(
                        modifier = Modifier
                            .wrapContentSize()
                            .height(200.dp)
                            .clip(RoundedRectShape)
                            .padding(HalfPadding),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        model = it,
                        clipToBounds = true,
                    )
                }
            }
        }
    } else {
        HorizontalPager(
            state = rememberPagerState { headerData.size },
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedRectShape)
                    .padding(HalfPadding),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                model = headerData[it],
                clipToBounds = true,
            )
        }
    }
}

@Composable
private fun DetailItem(title: String, value: String) {
    Row(modifier = Modifier.padding(horizontal = FabPadding)) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(horizontal = HalfPadding),
            text = value,
            style = MaterialTheme.typography.h6,
        )
    }
}
