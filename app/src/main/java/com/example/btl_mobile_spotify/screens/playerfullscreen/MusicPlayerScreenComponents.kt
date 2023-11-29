package com.example.btl_mobile_spotify.screens.playerfullscreen


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.components.IconBtn
import com.example.btl_mobile_spotify.components.RoundImageButton
import com.example.btl_mobile_spotify.utils.formatDuration

data class MusicControlButtonState(
    val isPlaying: Boolean = false,
    val isPlayPauseButtonEnabled: Boolean = true,
    val isSkipPrevButtonEnabled: Boolean = true,
    val isSkipNextButtonEnabled: Boolean = true,
    val onPlayPauseButtonClicked: () -> Unit = {},
    val onSkipNextButtonPressed: () -> Unit = {},
    val onSkipPrevButtonPressed: () -> Unit = {},
    val canLoop: Boolean = false,
    val onLoopButtonPressed: () -> Unit = {}
)

data class MusicSliderState(
    val sliderValue: Float = 0f,
    val timePassed: Long = 0L,
    val timeLeft: Long = 0L,
    val onValueChange: (Float) -> Unit = {}
) {
    val timePassedFormatted
        get() = timePassed.formatDuration()

    val timeLeftFormatted
        get() = timeLeft.formatDuration()
}

@Composable
fun PlayPauseButton(
    isPlaying: Boolean,
    isEnabled: Boolean = true,
    iconTint: Color = Color.White,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(65.dp)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconBtn(resIcon = if (isPlaying) R.drawable.ic_player_pause else R.drawable.ic_baseline_play_arrow_24, tint = Color.Black, onClick = onClick)
    }
}

@Composable
fun PreviousTrackButton(
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    IconBtn(resIcon = R.drawable.ic_player_back, onClick = onClick)

}


@Composable
fun NextTrackButton(
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    IconBtn(resIcon = R.drawable.ic_player_skip, onClick = onClick)

}

@Composable
fun LoopButton(
    onClick: () -> Unit,
    isLoopEnabled: Boolean = true
) {
    IconBtn(resIcon = if (!isLoopEnabled) R.drawable.ic_round_loop_24 else R.drawable.ic_round_loop_25, onClick = onClick, tint = Color.Unspecified)
}