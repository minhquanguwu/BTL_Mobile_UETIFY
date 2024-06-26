package com.example.btl_mobile_spotify.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import kotlinx.coroutines.flow.Flow
@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlist_table WHERE name LIKE '%' || :query || '%' ")
    fun getAllPlaylistsFlow(query: String): Flow<List<Playlist>>

    @Query("SELECT * FROM playlist_table")
    suspend fun getAllPlaylists(): List<Playlist>

    @Query("SELECT * FROM playlist_table WHERE id = :id")
    suspend fun getPlaylistById(id: String): Playlist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlists: List<Playlist>)

    @Query("DELETE FROM playlist_table")
    suspend fun deleteAllPlaylists()

    @Delete
    suspend fun deleteAllPlaylists(playlist: Playlist)

    @Update
    suspend fun updatePlaylist(playlist: Playlist)
    @Transaction
    suspend fun addSongToPlaylist(playlistId: String, songId: String) {
        val playlist = getPlaylistById(playlistId)
        if (playlist != null) {
            val updatedList = playlist.listMusicId.toMutableList().apply {
                add(songId)
            }
            val updatedPlaylist = playlist.copy(listMusicId = updatedList)
            updatePlaylist(updatedPlaylist)
        }
    }
}