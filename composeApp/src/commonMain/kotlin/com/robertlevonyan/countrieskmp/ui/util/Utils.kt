@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.robertlevonyan.countrieskmp.ui.util

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@Composable
fun isTablet(): Boolean {
    val windowSizeClass = calculateWindowSizeClass()
    return windowSizeClass.heightSizeClass == WindowHeightSizeClass.Expanded
            || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}
