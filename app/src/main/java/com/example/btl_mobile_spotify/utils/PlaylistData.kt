package com.example.btl_mobile_spotify.utils

import com.example.btl_mobile_spotify.data.models.local.Playlist
import java.util.UUID

val samplePlaylist = listOf(
    Playlist(
        id = UUID.randomUUID().toString(),
        name = "Playlist 1",
        userID = "minh quang",
        listMusicId = listOf("Around the World (La La La La La)","Nối Vòng Tay Lớn")
    ),
    Playlist(
        id = UUID.randomUUID().toString(),
        name = "Playlist 2",
        userID = "peter",
        listMusicId = listOf("Around the World (La La La La La)","Nối Vòng Tay Lớn")
    ),
)