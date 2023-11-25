package com.example.btl_mobile_spotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.btl_mobile_spotify.navigation.RootNavigationGraph
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
import com.google.android.gms.auth.api.identity.Identity
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.btl_mobile_spotify.screens.MainScreen
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var isBottomBarVisible = mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTL_Mobile_SpotifyTheme {
//                RootNavigationGraph(navController = rememberNavController(), LocalContext.current, lifecycleScope)
                MainScreen(navController = rememberNavController(), context = LocalContext.current,
                    lifecycleScope = lifecycleScope, isBottomBarVisible.value,
                    onToggleBottomBar = { isVisible ->
                        isBottomBarVisible.value = isVisible
                    })
            }
        }
    }
}
