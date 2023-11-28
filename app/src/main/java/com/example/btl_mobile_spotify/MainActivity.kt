package com.example.btl_mobile_spotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.btl_mobile_spotify.screens.MainScreen
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTL_Mobile_SpotifyTheme {
                MainScreen(navController = rememberNavController(), context = LocalContext.current,
                    lifecycleScope = lifecycleScope)
            }
        }
    }
}
