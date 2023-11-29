package com.example.btl_mobile_spotify.screens.playlist

import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

data class PlaylistScreenState(
    val playlistList: List<Playlist> = emptyList(),
    val musicList: List<Music> = emptyList(),
    val currentPlaylist: Playlist = Playlist(),
    val currentMusicList: List<Music> = emptyList()
//    val user : FirebaseUser?
)