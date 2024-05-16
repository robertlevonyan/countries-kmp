@file:OptIn(ExperimentalResourceApi::class)

package com.robertlevonyan.countrieskmp.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.robertlevonyan.countrieskmp.ui.theme.HalfPadding
import countries_kmp.composeapp.generated.resources.Res
import countries_kmp.composeapp.generated.resources.ic_close
import countries_kmp.composeapp.generated.resources.ic_search
import countries_kmp.composeapp.generated.resources.search_hint
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchComponent(
    backgroundColor: Color,
    onSearchInputChange: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxWidth().padding(HalfPadding)) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchInputChange(searchText)
            },
            label = { Text(stringResource(Res.string.search_hint)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = null,
                )
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_close),
                            contentDescription = null,
                        )
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = backgroundColor)
        )
    }
}
