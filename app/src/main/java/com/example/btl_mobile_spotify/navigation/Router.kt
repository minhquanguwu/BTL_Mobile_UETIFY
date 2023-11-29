package com.example.btl_mobile_spotify.navigation

interface Router {
    fun goHome()
    fun goPlayerFull(arg: Any?)
    fun goProfileScreen(arg: Any?)
    fun goAddToPlaylistScreen(arg: Any?)
    fun goListSongInPlaylistScreen(arg: Any?)
}