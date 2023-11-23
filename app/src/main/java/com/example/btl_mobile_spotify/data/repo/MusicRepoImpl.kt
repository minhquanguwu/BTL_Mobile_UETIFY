package com.example.btl_mobile_spotify.data.repo

import com.example.btl_mobile_spotify.data.local.datasource.RoomMusicDataSource
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.mapper.MusicMapper
import com.example.btl_mobile_spotify.data.remote.MusicDataSource
import com.example.btl_mobile_spotify.utils.Resource
import com.example.btl_mobile_spotify.utils.mapToUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicRepoImpl @Inject constructor(
    private val musicDataSource: MusicDataSource,
    private val musicDb: RoomMusicDataSource,
    private val musicMapper: MusicMapper
) : MusicRepo {

    override fun getAllSongsFlow(query: String) = musicDb.getAllSongsFlow(query)

    override suspend fun fetchAllMusic(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val musicResource = musicDataSource.getAllMusic()
        if (musicResource is Resource.Success)
            musicResource.data?.let { cacheMusic(musicMapper.toDomainList(it)) }
        emit(musicResource.mapToUnit())
    }

    override suspend fun uploadSong(music: Music): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val musicResource = musicDataSource.uploadMusic(musicMapper.toNetwork(music))
        emit(musicResource)
    }

    override suspend fun getAllSongs() = musicDb.getAllSongs()

    override suspend fun insertSongs(songs: List<Music>) = musicDb.insertSong(songs)

    override suspend fun deleteSong(song: Music) = musicDb.deleteSong(song)

    override suspend fun deleteAllSongs() = musicDb.deleteAllSongs()

    override suspend fun getSongById(id: String) = musicDb.getSongById(id)

    override suspend fun cacheMusic(songs: List<Music>) {
        deleteAllSongs()
        insertSongs(songs)
    }
}
