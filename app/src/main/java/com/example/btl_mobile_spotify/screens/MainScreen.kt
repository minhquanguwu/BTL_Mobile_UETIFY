package com.example.btl_mobile_spotify.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.btl_mobile_spotify.components.NavigationBar
import com.example.btl_mobile_spotify.navigation.NavigationContainer
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.navigation.RouterImpl
import com.example.btl_mobile_spotify.navigation.Screen
import com.example.btl_mobile_spotify.utils.Ext
import com.example.btl_mobile_spotify.utils.Ext.gradient


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavHostController, context: Context,
               lifecycleScope: LifecycleCoroutineScope) {
//    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route ?: Screen.Splash.route
    val router: Router = remember { RouterImpl(navController, route) }
    val isFullScreen = Screen.isFullScreen(route);
    Scaffold(
        bottomBar = {
            if (!isFullScreen) {
                Column(
                    modifier = Modifier
                        .gradient(
                            listOf(Color.Transparent, Color.Black),
                            Ext.GradientType.VERTICAL
                        )
                        .padding(top = 20.dp)
                ) {
//                    PlayerSmall(
//                        modifier = Modifier
//                            .clickableResize {
//                                router.goPlayerFull(null)
//                            }
//                    )
                    NavigationBar(route) { target ->
                        navController.apply {
                            navigate(target) {
                                restoreState = true
                                launchSingleTop = true
                                graph.startDestinationRoute?.let { route ->
                                    popUpTo(route = route) {
                                        saveState = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    ) {
        NavigationContainer(router = router, navController = navController, paddingValues = it,
            context = context, lifecycleScope = lifecycleScope)
    }
}

//@Preview
//@Composable
//fun PreviewMain() {
//    MainScreen()
//}