package com.example.exploredigimon.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{digimonId}") {
        fun createRoute(digimonId: Int): String = "detail/$digimonId"
    }
}
