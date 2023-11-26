package com.example.btl_mobile_spotify.screens.searchscreen

import com.example.btl_mobile_spotify.data.models.local.Music

data class SearchScreenState (
    val musicList: List<Music> = emptyList()
)