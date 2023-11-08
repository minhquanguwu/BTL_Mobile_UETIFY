package com.example.btl_mobile_spotify.screens.sign_in.native_method

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.btl_mobile_spotify.navigation.Graph
import com.example.btl_mobile_spotify.screens.sign_in.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class NativeLoginViewModel(private val navController: NavController) : ViewModel(){
    private val TAG = NativeLoginUIEvent::class.simpleName
    var nativeLoginUIState = mutableStateOf(NativeLoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

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
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
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
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"${it.isSuccessful}")

                if(it.isSuccessful){

                    loginInProgress.value = false
                    navController.navigate("profile")
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_login_failure")
                Log.d(TAG,"${it.localizedMessage}")

                loginInProgress.value = false

            }

    }
}