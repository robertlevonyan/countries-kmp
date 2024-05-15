package com.robertlevonyan.countrieskmp.ui

import androidx.compose.runtime.Composable
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.ui.details.DetailsScreen
import com.robertlevonyan.countrieskmp.ui.main.MainScreen
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.query
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.koin.compose.koinInject

//const val COUNTRY_ARG = "country"

@Composable
fun Navigation(
    isDarkTheme: Boolean,
    toggleTheme: (Boolean) -> Unit = {},
) {
    val navigator = rememberNavigator()
    val json = koinInject<Json>()

    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = NavigationScreens.Main.name,
    ) {
        scene(route = NavigationScreens.Main.name) {
            MainScreen(
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme,
                navigator = navigator,
            )
        }

        scene(route = NavigationScreens.Details.name) { backStackEntry ->
            val countryJson = backStackEntry.query<String>("country").orEmpty()
            val country = json.decodeFromString(Country.serializer(), countryJson)
            DetailsScreen(
                country = country,
                navigator = navigator,
            )
        }
    }
}

sealed class NavigationScreens(val name: String) {
    data object Main : NavigationScreens("/main")
    data object Details : NavigationScreens("/details")
}
