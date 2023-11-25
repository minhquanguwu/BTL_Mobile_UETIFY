package com.example.btl_mobile_spotify.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
import com.example.btl_mobile_spotify.utils.PAUSE_MUSIC_CD
import com.example.btl_mobile_spotify.utils.PLAY_MUSIC_CD
import com.example.btl_mobile_spotify.utils.getPlayPauseIcon

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

@Composable
fun PlayPauseButton(
    music: Music,
    isPlaying: Boolean,
    iconRelativeSize: Float = 0.4f,
    onPlayPauseButtonPressed: (Music) -> Unit
) {
    RoundImageButton(
        image = getPlayPauseIcon(isPlaying),
        iconTint = Color.Gray,
        iconRelativeSize = iconRelativeSize,
        backgroundColor = Color.Transparent,
        contentDescription = if (isPlaying) PAUSE_MUSIC_CD else PLAY_MUSIC_CD,
        onClick = {
            onPlayPauseButtonPressed(music)
        },
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f),
        iconOffset = if (isPlaying) 0.dp else 4.dp,
    )
}

@Composable
fun RoundImageButton(
    image: Int,
    iconTint: Color,
    iconRelativeSize: Float,
    backgroundColor: Color,
    contentDescription: String,
    modifier: Modifier = Modifier,
    iconOffset: Dp = 0.dp,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = backgroundColor),
        enabled = isEnabled
    ) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize(iconRelativeSize)
                .offset(x = iconOffset),
            tint = iconTint.copy(alpha = if (isEnabled) 1f else 0.5f)
        )
    }
}

@Preview()
@Composable
fun PlayButtonPreviewDark() {
    BTL_Mobile_SpotifyTheme {
        RoundImageButton(
            image = getPlayPauseIcon(true),
            iconTint = Color.Gray,
            backgroundColor = Color.Black,
            contentDescription = "Play Music",
            iconRelativeSize = 0.4f,
            modifier = Modifier.size(72.dp),
            iconOffset = 4.dp
        ) {}
    }
}
