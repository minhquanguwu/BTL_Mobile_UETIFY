package com.example.btl_mobile_spotify.data.remote

import com.example.btl_mobile_spotify.data.models.remote.MusicDTO
import com.example.btl_mobile_spotify.utils.Resource
interface MusicDataSource {

    suspend fun getAllMusic(): Resource<List<MusicDTO>>

    suspend fun uploadMusic(music: MusicDTO): Resource<Unit>
}
