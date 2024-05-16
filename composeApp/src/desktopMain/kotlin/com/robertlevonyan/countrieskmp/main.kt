@file:OptIn(ExperimentalResourceApi::class)

package com.robertlevonyan.countrieskmp

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import countries_kmp.composeapp.generated.resources.Res
import countries_kmp.composeapp.generated.resources.app_name
import countries_kmp.composeapp.generated.resources.ic_launcher
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    val state = rememberWindowState(
        size = DpSize(1200.dp, 800.dp),
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        icon = painterResource(Res.drawable.ic_launcher),
        state = state,
    ) {
        App()
    }
}
