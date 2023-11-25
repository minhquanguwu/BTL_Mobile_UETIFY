package com.example.btl_mobile_spotify.screens.playerfullscreen

import com.example.btl_mobile_spotify.data.models.local.Music

data class MusicPlayerScreenState(
    val image: String = "",
    val songName: String = "",
    val songArtistName: String = "",
    val allSongs: List<Music> = emptyList(),
    val currentSongIndex: Int = 0,
    val isPlaying: Boolean = false,
    val totalDuration: Long = 0L,
    val lyrics: String = "",
    val musicSliderState: MusicSliderState = MusicSliderState(),
    val musicControlButtonState: MusicControlButtonState = MusicControlButtonState()
) {
    val songsCount = allSongs.size
    val isBottomSheetVisible: Boolean
        get() = lyrics.isNotEmpty() && lyrics.isNotBlank()
}
