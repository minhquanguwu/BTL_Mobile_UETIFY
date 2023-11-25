package com.example.btl_mobile_spotify.screens.sign_up

sealed class SignUpUIEvent {
    data class NameChanged(val fullname:String) : SignUpUIEvent()
    data class EmailChanged(val email:String): SignUpUIEvent()
    data class PasswordChanged(val password: String) : SignUpUIEvent()

    //data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : SignupUIEvent()

    object SignUpButtonClicked : SignUpUIEvent()
}