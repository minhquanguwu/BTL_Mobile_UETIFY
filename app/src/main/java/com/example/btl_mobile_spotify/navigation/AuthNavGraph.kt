package com.example.btl_mobile_spotify.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.btl_mobile_spotify.screens.loginTest
import com.example.btl_mobile_spotify.screens.LoginScreen
import com.example.btl_mobile_spotify.screens.SignUpScreen
import com.example.btl_mobile_spotify.screens.sign_in.native_method.NativeLoginViewModel
import com.example.btl_mobile_spotify.screens.sign_up.SignUpUIState
import com.example.btl_mobile_spotify.screens.sign_up.SignUpViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController, NativeLoginViewModel(navController))
        }
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(navController, SignUpViewModel(navController))
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
}