package com.example.btl_mobile_spotify.screens.category

import com.example.btl_mobile_spotify.data.models.local.Music

data class CategoryScreenState (
    val musicListByGenre: List<Music> = emptyList()
)