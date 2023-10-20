package com.example.btl_mobile_spotify.screens.sign_in

data class SignInState(
    var isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)