package com.example.btl_mobile_spotify.screens.sign_in.native_method

import androidx.compose.ui.text.input.TextFieldValue

sealed class NativeLoginUIEvent{

    data class EmailChanged(val email: String): NativeLoginUIEvent()
    data class PasswordChanged(val password: String) : NativeLoginUIEvent()

    object LoginButtonClicked : NativeLoginUIEvent()
}