package com.robertlevonyan.countrieskmp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.ui.details.DetailsScreen
import com.robertlevonyan.countrieskmp.ui.main.MainScreen
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.query
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.koin.compose.koinInject

const val ARG_COUNTRY = "country"

@Composable
fun Navigation(
    isDarkTheme: MutableState<Boolean>,
    toggleTheme: (Boolean) -> Unit,
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
                isDarkTheme = isDarkTheme.value,
                toggleTheme = toggleTheme,
                navigator = navigator,
            )
        }

        scene(route = NavigationScreens.Details.name) { backStackEntry ->
            val cca2 = backStackEntry.query<String>(ARG_COUNTRY).orEmpty()
//            val countryJson = backStackEntry.query<String>(ARG_COUNTRY).orEmpty().ifEmpty {
//                backStackEntry.path.replace("${NavigationScreens.Details.name}?$ARG_COUNTRY=", "")
//            }
//            val country = json.decodeFromString(Country.serializer(), countryJson)
            DetailsScreen(
                cca2 = cca2,
                navigator = navigator,
            )
        }
    }
}

sealed class NavigationScreens(val name: String) {
    data object Main : NavigationScreens("/main")
    data object Details : NavigationScreens("/details")
}
