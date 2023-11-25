package com.example.btl_mobile_spotify.screens.sign_up

data class SignUpUIState(
    var fullname :String = "",
    var email  :String = "",
    var password  :String = "",
    //var privacyPolicyAccepted :Boolean = false,


    var fullnameError :Boolean = false,
    var emailError :Boolean = false,
    var passwordError : Boolean = false,
    //var privacyPolicyError:Boolean = false
)
