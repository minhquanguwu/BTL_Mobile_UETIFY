package com.example.btl_mobile_spotify.exoplayer.datasource


import com.example.btl_mobile_spotify.data.local.room.MusicDao
import com.example.btl_mobile_spotify.data.models.mapper.MusicMapper
import com.example.btl_mobile_spotify.data.remote.MusicDataSource
import com.example.btl_mobile_spotify.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseMusicPlayerDataSource @Inject constructor(
    private val dataSource: MusicDataSource,
    private val musicMapper: MusicMapper,
    private val musicDao: MusicDao
) : MusicPlayerDataSource() {

    override suspend fun getMusic() = withContext(Dispatchers.IO) {
        state = State.STATE_INITIALIZING
        val musicResource = dataSource.getAllMusic()
        val musicDTOList = if (musicResource is Resource.Success) {
            musicResource.data ?: emptyList()
        } else emptyList()
        allMusic = musicMapper.toDomainList(musicDTOList)
        musicDao.insertSong(allMusic)
        state = State.STATE_INITIALIZED
    }
}
