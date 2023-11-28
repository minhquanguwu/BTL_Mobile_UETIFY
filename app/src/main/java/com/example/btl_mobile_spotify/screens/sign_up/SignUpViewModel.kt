package com.example.btl_mobile_spotify.screens.sign_up

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.btl_mobile_spotify.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.example.btl_mobile_spotify.screens.sign_up.SignUpUIState
import com.example.btl_mobile_spotify.screens.sign_in.rules.Validator
import com.google.firebase.firestore.FirebaseFirestore


class SignUpViewModel(private val navController: NavController) : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName
    var signUpUIState = mutableStateOf(SignUpUIState())
    var allValidationsPassed = mutableStateOf(false)
    var isSignedUpFail = mutableStateOf(false)
    var isEmailValid = mutableStateOf(false)
    var isPasswordValid = mutableStateOf(false)
    var isNameValid = mutableStateOf(false)
    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.NameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    fullname = event.fullname
                )
            }

            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
            }

            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )

            }

            is SignUpUIEvent.SignUpButtonClicked -> {
                signUp()
            }
            else -> {}
        }
        validateDataWithRules()
    }


    private fun signUp() {
        createUserInFirebase(
            email = signUpUIState.value.email,
            password = signUpUIState.value.password
        )
    }

    fun validateDataWithRules() {
        val fNameResult = Validator.validateFullName(
            fName = signUpUIState.value.fullname
        )

        val emailResult = Validator.validateEmail(
            email = signUpUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = signUpUIState.value.password
        )

        signUpUIState.value = signUpUIState.value.copy(
            fullnameError = fNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )


        allValidationsPassed.value = fNameResult.status &&
                emailResult.status && passwordResult.status
        isEmailValid.value = emailResult.status
        isPasswordValid.value = passwordResult.status
        isNameValid.value = fNameResult.status
    }

    private fun createUserInFirebase(email: String, password: String) {
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    userId?.let {
                        createUserFirestore(it, signUpUIState.value.fullname, email, password)
                    }
                    navController.navigate(Screen.Home.route)
                    isSignedUpFail.value = false
                }
            }
            .addOnFailureListener {
                isSignedUpFail.value = true
            }
    }
    private fun createUserFirestore(userId: String, fullname: String, email: String, password: String) {
        val db = FirebaseFirestore.getInstance()

        val user = hashMapOf(
            "userId" to userId,
            "fullname" to fullname,
            "email" to email,
            "password" to password
        )

        db.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }


}