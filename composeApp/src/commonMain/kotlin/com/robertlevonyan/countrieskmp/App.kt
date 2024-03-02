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
import com.robertlevonyan.countrieskmp.ui.main.MainScreen
import com.robertlevonyan.countrieskmp.ui.theme.CountriesTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    var isDarkTheme by remember { mutableStateOf(isSystemInDarkTheme) }
    CountriesTheme(
        darkTheme = isDarkTheme
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MainScreen(
                isDarkTheme = isDarkTheme,
                toggleTheme = {
                    isDarkTheme = !isDarkTheme
                }
            )
        }
    }
}
