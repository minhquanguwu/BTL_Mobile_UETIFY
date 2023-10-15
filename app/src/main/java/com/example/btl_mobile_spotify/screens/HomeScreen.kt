package com.example.btl_mobile_spotify.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.btl_mobile_spotify.navigation.Router

@Composable
fun HomeScreen(paddingValues: PaddingValues = PaddingValues(), router: Router? = null) {
    Text(text = "HomeScreen")
}

@Preview (showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}