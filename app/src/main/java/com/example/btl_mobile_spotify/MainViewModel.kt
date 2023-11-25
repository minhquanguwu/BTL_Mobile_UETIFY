package com.example.btl_mobile_spotify

import android.support.v4.media.MediaBrowserCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btl_mobile_spotify.data.repo.MusicRepo
import com.example.btl_mobile_spotify.utils.Dispatcher
import com.example.btl_mobile_spotify.utils.MusicUseCase
import com.example.btl_mobile_spotify.utils.Resource
import com.example.btl_mobile_spotify.utils.getMusic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val musicUseCase: MusicUseCase,
    private val musicRepo: MusicRepo,
    private val dispatcher: Dispatcher
) : ViewModel() {

    init {
        subscribeToMusic()
    }

    private fun subscribeToMusic() = viewModelScope.launch {
        val resource = musicUseCase.subscribeToService()
        if (resource is Resource.Success)
            handleMusic(resource.data ?: emptyList())
    }

    private fun handleMusic(metaDataList: List<MediaBrowserCompat.MediaItem>) =
        viewModelScope.launch(dispatcher.main) {
            val music = metaDataList.map { it.getMusic() }
            musicRepo.cacheMusic(music)
        }

    override fun onCleared() {
        super.onCleared()
        musicUseCase.unsubscribeToService()
    }
}
