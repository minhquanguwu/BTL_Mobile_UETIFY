package com.example.btl_mobile_spotify.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist

@Database(entities = [Music::class, Playlist::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MusicXDatabase : RoomDatabase() {
    abstract fun getMusicDao(): MusicDao
    abstract fun getPlaylistDao(): PlaylistDao

}
