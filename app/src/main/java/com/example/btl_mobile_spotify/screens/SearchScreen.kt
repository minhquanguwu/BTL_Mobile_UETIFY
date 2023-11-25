package com.example.btl_mobile_spotify.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.btl_mobile_spotify.components.NavigationBar
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.navigation.RouterImpl
import com.example.btl_mobile_spotify.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(paddingValues: PaddingValues = PaddingValues(), navController: NavHostController, onToggleBottomBar: (Boolean) -> Unit) {
    onToggleBottomBar(true)
    Text(text = "SearchScreen")
}