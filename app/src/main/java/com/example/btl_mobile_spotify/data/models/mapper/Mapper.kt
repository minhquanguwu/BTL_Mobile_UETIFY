package com.example.btl_mobile_spotify.data.models.mapper

import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.data.models.remote.PlaylistDTO

interface Mapper<Network, Domain> {

    fun toDomain(network: Network): Domain

    fun toDomainList(networks: List<Network>): List<Domain>

    fun toNetwork(domain: Domain): Network

    fun toNetworkList(domains: List<Domain>): List<Network>

}
