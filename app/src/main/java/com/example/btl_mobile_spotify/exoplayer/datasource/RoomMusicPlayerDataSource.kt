package com.example.btl_mobile_spotify.exoplayer.datasource


import com.example.btl_mobile_spotify.data.local.room.MusicDao
import com.example.btl_mobile_spotify.data.models.mapper.MusicMapper
import com.example.btl_mobile_spotify.data.remote.MusicDataSource
import com.example.btl_mobile_spotify.exoplayer.datasource.MusicPlayerDataSource
import com.example.btl_mobile_spotify.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomMusicPlayerDataSource @Inject constructor(
    private val dataSource: MusicDataSource,
    private val musicMapper: MusicMapper,
    private val musicDao: MusicDao
) : MusicPlayerDataSource() {

    override suspend fun getMusic() = withContext(Dispatchers.IO) {
        state = State.STATE_INITIALIZING
        dataSource.getAllMusic().also {
            if (it is Resource.Success)
                musicDao.insertSong(musicMapper.toDomainList(it.data ?: emptyList()))
        }
        allMusic = musicDao.getAllSongs()
        state = State.STATE_INITIALIZED
    }
}
