package com.example.btl_mobile_spotify.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.btl_mobile_spotify.R

@Composable
fun PlayerFull() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        IconBtn(resIcon = R.drawable.ic_h_outline)
        IconBtn(resIcon = R.drawable.ic_player_back, modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(65.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconBtn(resIcon = R.drawable.ic_player_pause, tint = Color.Black)
        }
        IconBtn(resIcon = R.drawable.ic_player_skip, modifier = Modifier.weight(1f))
        IconBtn(resIcon = R.drawable.ic_remove)
    }
}

@Composable
@Preview
fun PlayerFullPreview() {
    PlayerFull()
}