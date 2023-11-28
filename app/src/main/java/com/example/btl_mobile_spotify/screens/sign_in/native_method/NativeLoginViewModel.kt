package com.example.btl_mobile_spotify.screens.sign_in.native_method

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.btl_mobile_spotify.navigation.Screen
import com.example.btl_mobile_spotify.screens.sign_in.rules.Validator
import com.example.btl_mobile_spotify.screens.user_data.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NativeLoginViewModel(private val navController: NavController) : ViewModel(){
    private val TAG = NativeLoginUIEvent::class.simpleName
    var nativeLoginUIState = mutableStateOf(NativeLoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)
    var isSignedInFail = mutableStateOf(false)
    fun onEvent(event: NativeLoginUIEvent) {
        when (event) {
            is NativeLoginUIEvent.EmailChanged -> {
                nativeLoginUIState.value = nativeLoginUIState.value.copy(
                    email = event.email
                )
            }

            is NativeLoginUIEvent.PasswordChanged -> {
                nativeLoginUIState.value = nativeLoginUIState.value.copy(
                    password = event.password
                )
            }

            is NativeLoginUIEvent.LoginButtonClicked -> {
                login()
            }

            is NativeLoginUIEvent.SignInSuccessfull -> {

            }
        }
        validateLoginUIDataWithRules()
    }

    fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = nativeLoginUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = nativeLoginUIState.value.password
        )

        nativeLoginUIState.value = nativeLoginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    private fun login() {
        loginInProgress.value = true
        val email = nativeLoginUIState.value.email
        val password = nativeLoginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    loginInProgress.value = false
                    navController.navigate(Screen.Home.route)
                    isSignedInFail.value = false
                }
            }
            .addOnFailureListener {
                loginInProgress.value = false
                isSignedInFail.value = true
            }
    }
    fun getSignedInUser(): UserInfo? = Firebase.auth.currentUser?.run {
        UserInfo(
            userId = uid,
            username = displayName,
            email = email?.toString()
        )

    }
}