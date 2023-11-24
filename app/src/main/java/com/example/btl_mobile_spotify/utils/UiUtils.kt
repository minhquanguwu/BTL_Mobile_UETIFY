package com.example.btl_mobile_spotify.utils

import com.example.btl_mobile_spotify.R

fun getPlayPauseIcon(isPlaying: Boolean) =
    if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play

const val PLAY_MUSIC_CD = "Play music"
const val PAUSE_MUSIC_CD = "Pause music"
