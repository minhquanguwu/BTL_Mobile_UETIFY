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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTL_Mobile_SpotifyTheme {
                RootNavigationGraph(navController = rememberNavController(), LocalContext.current, lifecycleScope)
            }
        }
    }
}
