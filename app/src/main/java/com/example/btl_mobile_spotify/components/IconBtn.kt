package com.example.btl_mobile_spotify.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun IconBtn(
    resIcon: Int,
    modifier: Modifier = Modifier,
    tint: Color = Color.White,
    selected: Boolean = true,
    selectedIcon: Int = resIcon,
    onClick: () -> Unit = {},
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = if (selected) {
                painterResource(id = selectedIcon)
            } else {
                painterResource(id = resIcon)
            },
            contentDescription = null,
            tint = tint
        )
    }
}
