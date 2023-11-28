package com.example.btl_mobile_spotify.data.models.mapper

import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.data.models.remote.MusicDTO
import com.example.btl_mobile_spotify.data.models.remote.PlaylistDTO
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Mapper<PlaylistDTO, Playlist> {
    override fun toDomain(network: PlaylistDTO): Playlist = Playlist(
        id = network.id,
        name = network.name,
        userID = network.userID,
    )

    override fun toDomainList(networks: List<PlaylistDTO>): List<Playlist> = networks.map {
        toDomain(it)
    }

    override fun toNetwork(domain: Playlist): PlaylistDTO = PlaylistDTO(
        id = domain.id,
        name = domain.name,
        userID = domain.userID,
    )

    override fun toNetworkList(domains: List<Playlist>): List<PlaylistDTO> = domains.map {
        toNetwork(it)
    }

}