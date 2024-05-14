@file:OptIn(ExperimentalResourceApi::class)

package com.robertlevonyan.countrieskmp.ui.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.ui.theme.DoublePadding
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import countries_kmp.composeapp.generated.resources.Res
import countries_kmp.composeapp.generated.resources.ic_close
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailsScreen(
    country: Country?,
    onBack: () -> Unit
) {

    Scaffold(
        modifier = Modifier,
        topBar = {
            Toolbar(countryName = "${country?.flag.orEmpty()} ${country?.name?.official.orEmpty()}") {
                onBack()
            }
        },
        content = { paddingValues ->
            DetailsContent(
                paddingValues = paddingValues,
                country = country,
            )
        },
    )
}

@Composable
private fun Toolbar(
    countryName: String?,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.fillMaxWidth().padding(end = DoublePadding),
                text = countryName.orEmpty(),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_close),
                    contentDescription = null,
                )
            }
        }
    )
}
