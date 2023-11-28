package com.example.btl_mobile_spotify.data.models.mapper

import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.data.models.remote.MusicDTO
import com.example.btl_mobile_spotify.data.models.remote.PlaylistDTO
import javax.inject.Inject

class MusicMapper @Inject constructor() : Mapper<MusicDTO, Music> {
    override fun toDomain(network: MusicDTO): Music = Music(
        id = network.id,
        title = network.title,
        duration = network.duration,
        artists = network.artists,
        imageUrl = network.imageUrl,
        musicUrl = network.musicUrl,
        genre = network.genre,
        lyrics = network.lyrics
    )

    override fun toDomainList(networks: List<MusicDTO>): List<Music> = networks.map {
        toDomain(it)
    }

    override fun toNetwork(domain: Music): MusicDTO = MusicDTO(
        id = domain.id,
        title = domain.title,
        duration = domain.duration,
        artists = domain.artists,
        imageUrl = domain.imageUrl,
        musicUrl = domain.musicUrl,
        genre = domain.genre,
        lyrics = domain.lyrics
    )

    override fun toNetworkList(domains: List<Music>): List<MusicDTO> = domains.map {
        toNetwork(it)
    }


}
