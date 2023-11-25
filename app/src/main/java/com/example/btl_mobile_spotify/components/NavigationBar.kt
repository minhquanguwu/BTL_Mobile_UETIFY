package com.example.btl_mobile_spotify.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btl_mobile_spotify.navigation.Screen

@Composable
fun NavigationBar(
    route: String,
    onRouteSelected: (targetRoute: String) -> Unit
) {
    val tabs = remember {
        listOf(
            Screen.Home,
            Screen.Search,
            Screen.Libs,
            Screen.Premium
        )
    }
    BottomNavigation(
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        elevation = 0.dp
    ) {
        tabs.forEach { item ->
            val targetRoute = item.route
            val selected = route.contains(targetRoute)
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        maxLines = 1,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),  
                selected = selected,
                onClick = { onRouteSelected(targetRoute) }
            )
        }
    }
}
