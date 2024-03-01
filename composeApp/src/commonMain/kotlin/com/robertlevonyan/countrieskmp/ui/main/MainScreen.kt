@file:OptIn(ExperimentalResourceApi::class)

package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import countrieskmp.composeapp.generated.resources.Res
import countrieskmp.composeapp.generated.resources.app_name
import countrieskmp.composeapp.generated.resources.light_mode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { Toolbar() },
        content = { paddingValues ->

        }
    )
}

@Composable
private fun Toolbar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.padding(horizontal = HalfPadding),
                text = stringResource(Res.string.app_name),
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(Res.drawable.light_mode),
                    contentDescription = null,
                    )
            }
        },

    )
}