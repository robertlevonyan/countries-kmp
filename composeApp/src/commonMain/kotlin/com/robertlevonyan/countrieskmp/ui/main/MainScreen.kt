package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import countries_kmp.composeapp.generated.resources.Res
import countries_kmp.composeapp.generated.resources.app_name
import countries_kmp.composeapp.generated.resources.dark_mode
import countries_kmp.composeapp.generated.resources.ic_filter
import countries_kmp.composeapp.generated.resources.light_mode
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    toggleTheme: (Boolean) -> Unit = {},
    navigator: Navigator,
) {
    var isFilterVisible by remember { mutableStateOf(false) }
    Scaffold(
        modifier = modifier,
        topBar = {
            Toolbar(
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme,
                toggleFilter = { isVisible ->
                    isFilterVisible = isVisible
                }
            )
        },
        content = { paddingValues ->
            MainContent(
                paddingValues = paddingValues,
                isDarkTheme = isDarkTheme,
                navigator = navigator,
                isFilterVisible = isFilterVisible,
            )
        }
    )
}

@Composable
private fun Toolbar(
    isDarkTheme: Boolean,
    toggleFilter: (Boolean) -> Unit,
    toggleTheme: (Boolean) -> Unit,
) {
    val icon = if (isDarkTheme) Res.drawable.light_mode else Res.drawable.dark_mode
    var isFilterVisible by remember { mutableStateOf(false) }
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.padding(horizontal = HalfPadding),
                text = stringResource(Res.string.app_name),
            )
        },
        actions = {
            IconButton(
                onClick = {
                    isFilterVisible = !isFilterVisible
                    toggleFilter(isFilterVisible)
                }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_filter),
                    contentDescription = null,
                )
            }
            IconToggleButton(
                checked = isDarkTheme,
                enabled = true,
                onCheckedChange = toggleTheme
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                )
            }
        },
    )
}
