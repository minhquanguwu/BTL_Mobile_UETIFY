package com.example.btl_mobile_spotify.navigation

import androidx.navigation.NavHostController
import com.example.btl_mobile_spotify.navigation.Routes.ROUTE_HOME

class RouterImpl(
    private val navHostController: NavHostController,
    private val startDestination: String = ROUTE_HOME
) : Router  {
    override fun goHome() {
        TODO("Not yet implemented")
    }

    override fun goAddToPlaylistScreen(arg: Any?) {
        navigate(Screen.Playlist)
    }

    override fun goPlayerFull(arg: Any?) {
        navigate(Screen.PlayerFull)
    }


    override fun goProfileScreen(arg: Any?) {
        navigate(Screen.Profile)
    }

    private fun navigate(
        screen: Screen,
        removeFromHistory: Boolean = false,
        singleTop: Boolean = false
    ) {
        navHostController.apply {
            navigate(screen.route) {
                if (removeFromHistory) {
                    if (singleTop) {
                        popUpTo(Screen.Home.route)
                    } else {
                        popUpTo(0) {
                            saveState = false
                        }
                    }

                } else {
                    restoreState = true
                }
                launchSingleTop = singleTop
            }
        }
    }
}