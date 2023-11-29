package com.example.btl_mobile_spotify.data.local.datasource

import com.example.btl_mobile_spotify.data.local.room.MusicDao
import com.example.btl_mobile_spotify.data.local.room.PlaylistDao
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import javax.inject.Inject

class RoomMusicDataSource @Inject constructor(private val musicDao: MusicDao, private val playlistDao : PlaylistDao) {

    fun getAllSongsFlow(query: String = "") = musicDao.getAllSongsFlow(query)

    suspend fun getAllSongs() = musicDao.getAllSongs()

    suspend fun getSongById(id: String) = musicDao.getSongById(id)

    suspend fun insertSong(songs: List<Music>) = musicDao.insertSong(songs)

    suspend fun deleteAllSongs() = musicDao.deleteAllSongs()

    suspend fun deleteSong(song: Music) = musicDao.deleteAllSongs(song)
    fun getAllPlaylistsFlow(query: String = "") = playlistDao.getAllPlaylistsFlow(query)
    suspend fun getAllPlaylists() = playlistDao.getAllPlaylists()
    suspend fun getPlaylistById(id: String) = playlistDao.getPlaylistById(id)
    suspend fun insertPlaylist(playlists: List<Playlist>) = playlistDao.insertPlaylist(playlists)
    suspend fun deleteAllPlaylists() = playlistDao.deleteAllPlaylists()
    suspend fun deleteSong(playlist: Playlist) = playlistDao.deleteAllPlaylists(playlist)
    suspend fun addSongToPlaylist(playlistId : String, songId : String) = playlistDao.addSongToPlaylist(playlistId, songId)

}
