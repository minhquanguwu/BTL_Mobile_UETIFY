package com.example.btl_mobile_spotify.data.repo

import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MusicRepo {
    fun getAllSongsFlow(query: String = ""): Flow<List<Music>>

    suspend fun getAllSongs(): List<Music>

    suspend fun getSongById(id: String): Music

    fun getSongByGenre(genre: String): Flow<List<Music>>

    suspend fun insertSongs(songs: List<Music>)

    suspend fun uploadSong(music: Music): Flow<Resource<Unit>>

    suspend fun deleteSong(song: Music)

    suspend fun deleteAllSongs()

    suspend fun fetchAllMusic(): Flow<Resource<Unit>>

    suspend fun cacheMusic(songs: List<Music>)

    fun getAllPlaylistsFlow(query: String = ""): Flow<List<Playlist>>
    suspend fun uploadPlaylist(playlist: Playlist): Flow<Resource<Unit>>
    suspend fun fetchAllPlaylist(): Flow<Resource<Unit>>
    suspend fun deleteAllPlaylists()
    suspend fun insertPlaylists(playlists: List<Playlist>)
    suspend fun cachePlaylist(playlists: List<Playlist>)

    suspend fun addSongToPlaylist(song: Music, playlist: Playlist)

}