package com.example.btl_mobile_spotify.navigation

import androidx.annotation.DrawableRes
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_HOME
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_INFO
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_LIBS
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_LOGIN
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_PREMIUM
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_SEARCH
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_SIGNUP
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_SONG_FULL
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_SPLASH
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_START

object Routes {
    const val ROUTE_HOME = "ROUTE_HOME"
    const val ROUTE_SEARCH = "ROUTE_SEARCH"
    const val ROUTE_LIBS = "ROUTE_LIBS"
    const val ROUTE_PREMIUM = "ROUTE_PREMIUM"
    const val ROUTE_SPLASH = "ROUTE_SPLASH"
    const val ROUTE_SONG_FULL = "ROUTE_SONG_FULL"
    const val ROUTE_INFO = "ROUTE_INFO"
    const val ROUTE_LOGIN = "ROUTE_LOGIN"
    const val ROUTE_SIGNUP = "ROUTE_SIGNUP"
    const val ROUTE_START = "ROUTE_START"
}

sealed class Screen(
    val route: String,
    var tag: String = route,
    val title: String = "",
    @DrawableRes val icon: Int = 0
) {
    object Home : Screen(route = ROUTE_HOME, title = "Home", icon = R.drawable.ic_home_filled)
    object Search : Screen(route = ROUTE_SEARCH, title = "Search", icon = R.drawable.ic_search_big)
    object Libs :
        Screen(route = ROUTE_LIBS, title = "Your Library", icon = R.drawable.ic_library_big)

    object Premium : Screen(route = ROUTE_PREMIUM, title = "Premium", icon = R.drawable.ic_premium)
    object Splash : Screen(route = ROUTE_SPLASH)

    object PlayerFull : Screen(route = ROUTE_SONG_FULL)

    object Profile : Screen(route = ROUTE_INFO)
    object Login : Screen(route = ROUTE_LOGIN)
    object SignUp : Screen(route = ROUTE_SIGNUP)
    object Start : Screen(route = ROUTE_START)
}