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
}