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

    var signUpInProgress = mutableStateOf(false)
    var isSignedUpFail = mutableStateOf(false)
    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.NameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    fullname = event.fullname
                )
                printState()
            }


            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignUpUIEvent.SignUpButtonClicked -> {
                signUp()
            }

//            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
//                signUpUIState.value = signUpUIState.value.copy(
//                    privacyPolicyAccepted = event.status
//                )
//            }
            else -> {}
        }
        validateDataWithRules()
    }


    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = signUpUIState.value.email,
            password = signUpUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFullName(
            fName = signUpUIState.value.fullname
        )

        val emailResult = Validator.validateEmail(
            email = signUpUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = signUpUIState.value.password
        )

//        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
//            statusValue = registrationUIState.value.privacyPolicyAccepted
//        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        //Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        signUpUIState.value = signUpUIState.value.copy(
            fullnameError = fNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )


        allValidationsPassed.value = fNameResult.status &&
                emailResult.status && passwordResult.status

    }


    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, signUpUIState.value.toString())
    }


    private fun createUserInFirebase(email: String, password: String) {

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false
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
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
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
            // Add more fields if needed
        )

        db.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: $fullname")
                // You can perform additional actions after successfully adding the user to Firestore
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                // Handle the error appropriately
            }
    }


}