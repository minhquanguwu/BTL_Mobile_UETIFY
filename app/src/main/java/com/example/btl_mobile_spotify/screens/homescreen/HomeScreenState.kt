package com.example.btl_mobile_spotify.screens.homescreen

import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.utils.MusicState


data class HomeScreenState(
    val musicList: List<Music> = emptyList(),
    val currentPlayingMusic: Music? = null,
    val searchBarText: String = "",
    val musicState: MusicState = MusicState.NONE,
    val playlistList: List<Playlist> = emptyList()
) {
    val isMusicBottomBarVisible =
        currentPlayingMusic != null && (musicState == MusicState.PLAYING || musicState == MusicState.PAUSED)

    val isMusicPlaying = musicState == MusicState.PLAYING
}
