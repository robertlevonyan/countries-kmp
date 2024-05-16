package com.robertlevonyan.countrieskmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.robertlevonyan.countrieskmp.di.getDiModules
import com.robertlevonyan.countrieskmp.ui.Navigation
import com.robertlevonyan.countrieskmp.ui.theme.CountriesTheme
import moe.tlaster.precompose.PreComposeApp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isDarkTheme = remember { mutableStateOf(isSystemInDarkTheme) }

    KoinApplication(application = { modules(getDiModules()) }) {
        PreComposeApp {
            CountriesTheme(darkTheme = isDarkTheme.value) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Navigation(
                        isDarkTheme = isDarkTheme,
                        toggleTheme = { isDark -> isDarkTheme.value = isDark },
                    )
                }
            }
        }
    }
}
