package com.example.btl_mobile_spotify.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_table")
data class Playlist (
    @PrimaryKey
    val id: String,
    val name: String,
    val userID: String,
    val listMusicId: List<String>,
) {
    constructor() : this("", "", "", emptyList())
}