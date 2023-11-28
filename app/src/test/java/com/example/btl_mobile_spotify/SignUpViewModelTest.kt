package com.example.btl_mobile_spotify

import androidx.navigation.NavController
import com.example.btl_mobile_spotify.screens.sign_up.SignUpViewModel
import com.example.btl_mobile_spotify.screens.sign_up.SignUpUIState
import com.example.btl_mobile_spotify.screens.sign_up.SignUpUIEvent
import com.example.btl_mobile_spotify.screens.sign_in.rules.Validator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class SignUpViewModelTest {

    private lateinit var signUpViewModel: SignUpViewModel

    @Before
    fun setup() {
        signUpViewModel = SignUpViewModel(mock(NavController::class.java))
    }

    @Test
    fun `validateDataWithRules should return true when all fields are valid`() {
        val uiState = SignUpUIState("Linh", "abc@gmail.com", "password",
            false, false, false)
        signUpViewModel.signUpUIState.value = uiState
        signUpViewModel.validateDataWithRules()
        assertTrue(signUpViewModel.allValidationsPassed.value)
        assertTrue(signUpViewModel.isNameValid.value)
        assertTrue(signUpViewModel.isEmailValid.value)
        assertTrue(signUpViewModel.isPasswordValid.value)
    }

    @Test
    fun `validateDataWithRules should return false when name is invalid`() {
        val uiState = SignUpUIState("J", "abc@gmail.com", "password",
            false, false, false)
        signUpViewModel.signUpUIState.value = uiState
        signUpViewModel.validateDataWithRules()
        assertFalse(signUpViewModel.allValidationsPassed.value)
        assertFalse(signUpViewModel.isNameValid.value)
        assertTrue(signUpViewModel.isEmailValid.value)
        assertTrue(signUpViewModel.isPasswordValid.value)
    }

    @Test
    fun `validateDataWithRules should return false when email is invalid`() {
        val uiState = SignUpUIState("Linh", "", "password",
            false, false, false)
        signUpViewModel.signUpUIState.value = uiState
        signUpViewModel.validateDataWithRules()
        assertFalse(signUpViewModel.allValidationsPassed.value)
        assertTrue(signUpViewModel.isNameValid.value)
        assertFalse(signUpViewModel.isEmailValid.value)
        assertTrue(signUpViewModel.isPasswordValid.value)
    }

    @Test
    fun `validateDataWithRules should return false when password is invalid`() {
        val uiState = SignUpUIState("Linh", "abc@gmail.com", "pas",
            false, false, false)
        signUpViewModel.signUpUIState.value = uiState
        signUpViewModel.validateDataWithRules()
        assertFalse(signUpViewModel.allValidationsPassed.value)
        assertTrue(signUpViewModel.isNameValid.value)
        assertTrue(signUpViewModel.isEmailValid.value)
        assertFalse(signUpViewModel.isPasswordValid.value)
    }


}
