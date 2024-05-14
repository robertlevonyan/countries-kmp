package com.robertlevonyan.countrieskmp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.robertlevonyan.countrieskmp.entity.Country
import com.robertlevonyan.countrieskmp.ui.details.DetailsScreen
import com.robertlevonyan.countrieskmp.ui.main.MainScreen
import kotlinx.serialization.json.Json
import org.kodein.di.compose.localDI
import org.kodein.di.instance

const val COUNTRY_ARG = "country"

@Composable
fun Navigation(
    isDarkTheme: Boolean,
    toggleTheme: (Boolean) -> Unit = {},
) {
    val navController = rememberNavController()
    val di = localDI()
    val json: Json by di.instance()
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.Main.name
    ) {
        composable(route = NavigationScreens.Main.name) {
            MainScreen(
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme,
                navController = navController,
            ) { country ->
                selectedCountry = country
            }
        }
        composable(
            route = NavigationScreens.Details.name,
//            route = "${NavigationScreens.Details.name}/{country}",
//            arguments = listOf(
//                navArgument(COUNTRY_ARG) { type = NavType.StringType },
//            )
        ) { backStackEntry ->
//            val countryJson = backStackEntry.arguments?.getString(COUNTRY_ARG).orEmpty()
//            val country = json.decodeFromString(Country.serializer(), countryJson)
            DetailsScreen(country = selectedCountry) {
                selectedCountry = null
                navController.popBackStack()

            }
        }
    }
}

sealed class NavigationScreens(val name: String) {
    data object Main : NavigationScreens("main")
    data object Details : NavigationScreens("details")
}
