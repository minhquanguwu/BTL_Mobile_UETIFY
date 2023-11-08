package com.example.btl_mobile_spotify.screens.sign_in.additional_method

import androidx.lifecycle.ViewModel
import com.example.btl_mobile_spotify.screens.sign_in.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AdditionalSignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(AdditionalSignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = (result.data != null),
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { AdditionalSignInState() }
    }
}