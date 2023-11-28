package com.example.btl_mobile_spotify.screens.playlist

import androidx.lifecycle.ViewModel
import com.example.btl_mobile_spotify.exoplayer.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection
): ViewModel() {

}