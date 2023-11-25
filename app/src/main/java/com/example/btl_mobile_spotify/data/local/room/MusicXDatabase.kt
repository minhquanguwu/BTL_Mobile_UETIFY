package com.example.btl_mobile_spotify.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.btl_mobile_spotify.data.models.local.Music

@Database(entities = [Music::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MusicXDatabase : RoomDatabase() {
    abstract fun getMusicDao(): MusicDao
}
