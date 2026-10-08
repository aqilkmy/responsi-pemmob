package com.example.exploredigimon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exploredigimon.ui.screens.detail.DetailScreen
import com.example.exploredigimon.ui.screens.home.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onDigimonClick = { digimonId ->
                    navController.navigate(Screen.Detail.createRoute(digimonId))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("digimonId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val digimonId = backStackEntry.arguments?.getInt("digimonId") ?: 1
            DetailScreen(
                digimonId = digimonId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
