package com.example.btl_mobile_spotify.screens.libraryscreen

import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist

data class LibraryScreenState(
    val playlistList: List<Playlist> = emptyList(),
    val musicList: List<Music> = emptyList()
)
