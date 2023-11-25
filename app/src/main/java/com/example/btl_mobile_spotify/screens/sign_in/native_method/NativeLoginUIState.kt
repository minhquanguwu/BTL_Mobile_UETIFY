package com.example.btl_mobile_spotify.screens.sign_in.native_method

data class NativeLoginUIState(
    var email: String = "",
    var password: String = "",
    var isSignedIn: Boolean = false,
    var emailError:Boolean = false,
    var passwordError: Boolean = false
)
