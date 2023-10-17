package com.example.btl_mobile_spotify.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.btl_mobile_spotify.screens.MainScreen

fun NavGraphBuilder.contentNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CONTENT,
        startDestination = ContentScreen.Main.route
    ) {
        composable(route = ContentScreen.Main.route) {
            MainScreen()
        }

    }
}

sealed class ContentScreen(val route: String) {
    object Main : ContentScreen(route = "MAIN")
}