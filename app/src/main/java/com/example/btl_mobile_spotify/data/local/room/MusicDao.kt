package com.example.btl_mobile_spotify.data.local.room

import androidx.room.*
import com.example.btl_mobile_spotify.data.models.local.Music
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Query("SELECT * FROM music_table WHERE title LIKE '%' || :query || '%' OR artists LIKE '%' || :query || '%'")
    fun getAllSongsFlow(query: String): Flow<List<Music>>

    @Query("SELECT * FROM music_table")
    suspend fun getAllSongs(): List<Music>

    @Query("SELECT * FROM music_table WHERE id = :id")
    suspend fun getSongById(id: String): Music

    @Query("SELECT * FROM music_table WHERE genre = :genre")
    fun getSongByGenre(genre: String): Flow<List<Music>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(songs: List<Music>)

    @Query("DELETE FROM music_table")
    suspend fun deleteAllSongs()

    @Delete
    suspend fun deleteAllSongs(song: Music)
}
