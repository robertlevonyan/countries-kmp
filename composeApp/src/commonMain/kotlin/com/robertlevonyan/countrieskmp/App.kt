package com.robertlevonyan.countrieskmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.robertlevonyan.countrieskmp.di.getDiModules
import com.robertlevonyan.countrieskmp.di.sqliteModule
import com.robertlevonyan.countrieskmp.ui.Navigation
import com.robertlevonyan.countrieskmp.ui.theme.CountriesTheme
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinApplication

@Composable
fun App(isAndroidApp: Boolean = false) {
    if (isAndroidApp) {
        AppContent()
    } else {
        KoinApplication(application = { modules(getDiModules() + sqliteModule) }) {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isDarkTheme = remember { mutableStateOf(isSystemInDarkTheme) }

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
