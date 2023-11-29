package com.example.btl_mobile_spotify.screens.playerfullscreen

import Sizes.MEDIUM
import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.components.IconBtn
import com.example.btl_mobile_spotify.components.ImageCrop
import com.example.btl_mobile_spotify.components.PlayerFull
import com.example.btl_mobile_spotify.components.RoundImageButton
import com.example.btl_mobile_spotify.components.TextSubtitle
import com.example.btl_mobile_spotify.components.TextTitle
import com.example.btl_mobile_spotify.components.TopBar
import com.example.btl_mobile_spotify.utils.Ext
import com.example.btl_mobile_spotify.utils.Ext.gradient

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicPlayerScreen(
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel = hiltViewModel(),
    ) {
    Log.d("Song", "${viewModel.uiState.value.songName}")
    val scrollState = rememberScrollState()
    val sliderPosition = remember { mutableStateOf(0f) }
    val primaryColor = MaterialTheme.colors.primary
    val controlsColor = remember { Animatable(primaryColor) }
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing)
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .gradient(
                listOf(Color.Red, Color.Transparent, Color.Transparent),
                Ext.GradientType.VERTICAL
            )

    ) {
        TopBar(
            navigationIcon = {
                IconBtn(resIcon = R.drawable.ic_down)
            },
            title = {
                Column(
                    modifier = Modifier.weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "playlist", color = Color.White)
//                    TextTitle(text = "AUTHOR", fontSize = 18.sp)
                }
            },
            actions = {
                IconBtn(resIcon = R.drawable.ic_dots)
            }
        )
        Column(Modifier.padding(horizontal = 22.dp)) {

            ImageCrop(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 60.dp)
                    .height(300.dp)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .border(2.dp, Color.Gray, CircleShape)
                    .graphicsLayer {
                        rotationZ = angle
                    }
                    ,
                data = viewModel.uiState.value.image
            )

            TextTitle(text = viewModel.uiState.value.songName)
            TextSubtitle(text = viewModel.uiState.value.songArtistName)


            MusicSlider(
//                sliderColor = controlsColor,
                state = viewModel.uiState.value.musicSliderState
            )

            MusicControlButtons(
                tint = controlsColor.value,
                viewModel.uiState.value.musicControlButtonState,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconBtn(resIcon = R.drawable.ic_sound)
                Spacer(modifier = Modifier.weight(2f))
                IconBtn(resIcon = R.drawable.ic_baseline_share_24)
            }
            Text(text = viewModel.uiState.value.lyrics)
        }

    }
}
@Composable
fun customSliderColors(): SliderColors = SliderDefaults.colors(
    inactiveTrackColor = Color(0x337C7C7C),
    activeTrackColor = Color.White,
    thumbColor = Color.White
)


@Composable
fun MusicSlider(
    state: MusicSliderState
) {
    Slider(
        value = state.sliderValue,
        modifier = Modifier.fillMaxWidth(),
        colors = customSliderColors(),
        onValueChange = state.onValueChange,
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = state.timePassedFormatted,
            style = MaterialTheme.typography.body2,
            color = Color.White,
        )

        Text(
            text = state.timeLeftFormatted,
            style = MaterialTheme.typography.body2,
            color = Color.White,
        )
    }
}

@Composable
private fun MusicControlButtons(
    tint: Color,
    state: MusicControlButtonState,
    musicPlayViewModel: MusicPlayerViewModel = hiltViewModel()
//    onPlayListButtonPress: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        LoopButton(
            state.onLoopButtonPressed,
            musicPlayViewModel._musicControlButtonState.value.canLoop,
        )


        PreviousTrackButton(
            true,
            state.onSkipPrevButtonPressed
        )
        PlayPauseButton(
            state.isPlaying,
            state.isPlayPauseButtonEnabled,
            tint,
            state.onPlayPauseButtonClicked
        )
        NextTrackButton(
            state.isSkipNextButtonEnabled,
            state.onSkipNextButtonPressed
        )
//        PlayListButton(tint, true, onPlayListButtonPress)
        IconBtn(resIcon = R.drawable.ic_h_outline)


    }
}
