package com.example.btl_mobile_spotify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btl_mobile_spotify.screens.StartScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.START
    ) {
        authNavGraph(navController = navController)
        contentNavGraph(navController = navController)
        composable(route = Graph.START) {
            StartScreen(navController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val START = "home_graph"
    const val CONTENT = "details_graph"
}