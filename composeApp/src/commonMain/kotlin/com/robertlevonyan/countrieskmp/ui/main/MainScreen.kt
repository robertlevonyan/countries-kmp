@file:OptIn(ExperimentalResourceApi::class)

package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import countries_kmp.composeapp.generated.resources.Res
import countries_kmp.composeapp.generated.resources.app_name
import countries_kmp.composeapp.generated.resources.dark_mode
import countries_kmp.composeapp.generated.resources.light_mode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Toolbar(
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme,
            )
        },
        content = { paddingValues ->
            MainContent(paddingValues = paddingValues)
        }
    )
}

@Composable
private fun Toolbar(
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit,
) {
    val icon = if (isDarkTheme) Res.drawable.light_mode else Res.drawable.dark_mode
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.padding(horizontal = HalfPadding),
                text = stringResource(Res.string.app_name),
            )
        },
        actions = {
            IconButton(onClick = toggleTheme) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                )
            }
        },
    )
}
