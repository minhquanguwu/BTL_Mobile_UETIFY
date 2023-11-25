package com.example.btl_mobile_spotify.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btl_mobile_spotify.screens.homescreen.HomeScreen
import com.example.btl_mobile_spotify.screens.LibsScreen
import com.example.btl_mobile_spotify.screens.PremiumScreen
import com.example.btl_mobile_spotify.screens.SearchScreen
import com.example.btl_mobile_spotify.screens.SplashScreen
import com.example.btl_mobile_spotify.screens.playerfullscreen.MusicPlayerScreen

@Composable
fun NavigationContainer(
    router: Router,
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    val startDestination = remember { mutableStateOf(Screen.Splash.route) }
    LaunchedEffect(startDestination) {
        if (startDestination.value == Screen.Home.route) {
            router.goHome()
        }
    }
    NavHost(navController = navController, startDestination = startDestination.value) {
        composable(Screen.Home.route) {
            HomeScreen(paddingValues, router)
        }
        composable(Screen.Search.route) {
            SearchScreen(paddingValues)
        }
        composable(Screen.Libs.route) {
            LibsScreen(paddingValues, router)
        }
        composable(Screen.Premium.route) {
            PremiumScreen(paddingValues)
        }
        composable(Screen.Splash.route) {
            SplashScreen(
                goBack = {
                    startDestination.value = Screen.Home.route
                }
            )
        }
        composable(Screen.PlayerFull.route) {
            MusicPlayerScreen()
        }
    }

}