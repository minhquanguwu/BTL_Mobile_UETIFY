package com.example.btl_mobile_spotify

import androidx.navigation.NavController
import com.example.btl_mobile_spotify.screens.sign_in.native_method.NativeLoginUIState
import com.example.btl_mobile_spotify.screens.sign_in.native_method.NativeLoginViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class NativeLoginViewModelTest {

    private lateinit var nativeLoginViewModel: NativeLoginViewModel

    @Before
    fun setup() {
        nativeLoginViewModel = NativeLoginViewModel(mock(NavController::class.java))
    }

    @Test
    fun `validateDataWithRules should return true when all fields are valid`() {
        val uiState = NativeLoginUIState("abc@gmail.com", "abc123",
            false, false, false)
        nativeLoginViewModel.nativeLoginUIState.value = uiState
        nativeLoginViewModel.validateLoginUIDataWithRules()
        assertTrue(nativeLoginViewModel.allValidationsPassed.value)
    }

    @Test
    fun `validateDataWithRules should return false when password is invalid`() {
        val uiState = NativeLoginUIState("abc@gmail.com", "a",
            false, false, false)
        nativeLoginViewModel.nativeLoginUIState.value = uiState
        nativeLoginViewModel.validateLoginUIDataWithRules()
        assertFalse(nativeLoginViewModel.allValidationsPassed.value)
    }

    @Test
    fun `validateDataWithRules should return false when email is invalid`() {
        val uiState = NativeLoginUIState("", "abc123",
            false, false, false)
        nativeLoginViewModel.nativeLoginUIState.value = uiState
        nativeLoginViewModel.validateLoginUIDataWithRules()
        assertFalse(nativeLoginViewModel.allValidationsPassed.value)
    }
}
