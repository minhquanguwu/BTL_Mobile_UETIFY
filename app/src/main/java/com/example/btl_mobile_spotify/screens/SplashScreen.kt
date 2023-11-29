package com.example.btl_mobile_spotify.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.ui.theme.Active
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(goBack: () -> Unit = {}) {
    BackHandler {
        goBack()
    }
    LaunchedEffect(true) {
        delay(1500)
        goBack()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}