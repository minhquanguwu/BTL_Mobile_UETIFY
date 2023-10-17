package com.example.btl_mobile_spotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.btl_mobile_spotify.navigation.RootNavigationGraph
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTL_Mobile_SpotifyTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
