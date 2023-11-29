package com.example.btl_mobile_spotify.navigation

import androidx.annotation.DrawableRes
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_CATEGORY
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_HOME
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_LIBS
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_PLAYER_FULL
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_PLAYLIST
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_PREMIUM
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_SEARCH
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_SPLASH
import com.example.btl_mobile_spotify.navigation.Routes.fullScreenRoutes

object Routes {
    const val ROUTE_HOME = "ROUTE_HOME"
    const val ROUTE_SEARCH = "ROUTE_SEARCH"
    const val ROUTE_LIBS = "ROUTE_LIBS"
    const val ROUTE_PREMIUM = "ROUTE_PREMIUM"
    const val ROUTE_SPLASH = "ROUTE_SPLASH"
    const val ROUTE_PLAYER_FULL = "ROUTE_PLAYER_FULL"
    const val ROUTE_INFO = "ROUTE_INFO"
    const val ROUTE_LOGIN = "ROUTE_LOGIN"
    const val ROUTE_SIGNUP = "ROUTE_SIGNUP"
    const val ROUTE_START = "ROUTE_START"
    const val ROUTE_PLAYLIST = "ROUTE_PLAYLIST"
    const val ROUTE_CATEGORY = "ROUTE_CATEGORY"

    val fullScreenRoutes = listOf(
        ROUTE_SPLASH,
        ROUTE_PLAYER_FULL,
        ROUTE_START,
        ROUTE_LOGIN,
        ROUTE_SIGNUP,
        ROUTE_INFO

    )
}

sealed class Screen(
    val route: String,
    var tag: String = route,
    val title: String = "",
    @DrawableRes val icon: Int = 0
) {
    object Home : Screen(route = ROUTE_HOME, title = "Home", icon = R.drawable.ic_home_filled)
    object Search : Screen(route = ROUTE_SEARCH, title = "Search", icon = R.drawable.ic_search_big)
    object Category: Screen(route = ROUTE_CATEGORY, title = "Category")
    object Libs : Screen(route = ROUTE_LIBS, title = "Your Library", icon = R.drawable.ic_library_big)
    object Playlist : Screen(route = ROUTE_PLAYLIST, title = "Your Playlist")

    object Premium : Screen(route = ROUTE_PREMIUM, title = "Premium", icon = R.drawable.ic_premium)
    object Splash : Screen(route = ROUTE_SPLASH)
    object PlayerFull : Screen(route = ROUTE_PLAYER_FULL)
    object Profile : Screen(route = Routes.ROUTE_INFO)
    object Login : Screen(route = Routes.ROUTE_LOGIN)
    object SignUp : Screen(route = Routes.ROUTE_SIGNUP)
    object Start : Screen(route = Routes.ROUTE_START)

    companion object {
        fun isFullScreen(route: String?): Boolean {
            return fullScreenRoutes.contains(route)
        }
    }
}