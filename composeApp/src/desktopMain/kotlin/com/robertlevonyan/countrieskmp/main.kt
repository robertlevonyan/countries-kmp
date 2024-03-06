@file:OptIn(ExperimentalResourceApi::class)

package com.robertlevonyan.countrieskmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import countries_kmp.composeapp.generated.resources.Res
import countries_kmp.composeapp.generated.resources.app_name
import countries_kmp.composeapp.generated.resources.ic_launcher
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        icon = painterResource(Res.drawable.ic_launcher),
    ) {
        App()
    }
}