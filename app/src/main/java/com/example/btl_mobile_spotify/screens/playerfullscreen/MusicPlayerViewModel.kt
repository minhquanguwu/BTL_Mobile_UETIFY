package com.example.btl_mobile_spotify.screens.playerfullscreen

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.repo.MusicRepo
import com.example.btl_mobile_spotify.exoplayer.isPlaying
import com.example.btl_mobile_spotify.utils.MusicUseCase
import com.example.btl_mobile_spotify.utils.getArtistsString
import com.example.btl_mobile_spotify.utils.getMusic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    private val musicRepo: MusicRepo,
//    private val lyricsRepo: LyricsRepo,
    private val musicUseCase: MusicUseCase
) : ViewModel() {

    private val songs =
        musicRepo.getAllSongsFlow().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _uiState = mutableStateOf(
        MusicPlayerScreenState(musicControlButtonState = getMusicControlButtonState())
    )
    val uiState: State<MusicPlayerScreenState> = _uiState

    private val playbackState = musicUseCase.playbackState
    private val currentSong = musicUseCase.currentSong
//    private val musicControlButtonState = MusicControlButtonState()

    val _musicControlButtonState = mutableStateOf(
        MusicControlButtonState()
    )
    val musicControlButtonState: State<MusicControlButtonState> = _musicControlButtonState
    var isLoopAvailable = false
//    var isLoopAvailable = mutableStateOf(false)
    init {
//        setMusicControlButtonState()
        setMusicSliderState()
        collectSongs()
        collectTimePassed()
        collectPlaybackState()
    }

    private fun setMusicControlButtonState() = viewModelScope.launch {
        _uiState.value = uiState.value.copy(
            musicControlButtonState = uiState.value.musicControlButtonState.copy()
        )
    }

    private fun getMusicControlButtonState() = MusicControlButtonState(
        onPlayPauseButtonClicked = this::onPlayPauseButtonPressed,
        onSkipNextButtonPressed = this::onNextTrackPressed,
        onSkipPrevButtonPressed = this::onPrevTrackPressed,
        onLoopButtonPressed = this::onLoopTrackPressed,
    )

    private fun setMusicSliderState() = viewModelScope.launch {
        _uiState.value = uiState.value.copy(
            musicSliderState = uiState.value.musicSliderState.copy(
                onValueChange = this@MusicPlayerViewModel::onSeekBarValueChanged
            )
        )
    }

    private fun onSeekBarValueChanged(value: Float) = viewModelScope.launch {
        _uiState.value = uiState.value.copy(
            musicSliderState = uiState.value.musicSliderState.copy(sliderValue = value)
        )
        val seekTime = (value * uiState.value.totalDuration).toLong()
        musicUseCase.seekTo(seekTime)
    }

//    private fun onLoopTrackPressed() = viewModelScope.launch {
//        if (musicControlButtonState.canLoop == true) musicControlButtonState.canLoop = false
//        else musicControlButtonState.canLoop = true
//        isLoopAvailable.value = musicControlButtonState.canLoop
//    }

    private fun onLoopTrackPressed() {
        val currentState = _musicControlButtonState.value
        val updatedState = currentState.copy(canLoop = !currentState.canLoop)
        _musicControlButtonState.value = updatedState
        isLoopAvailable = updatedState.canLoop
    }

    private fun onNextTrackPressed() = viewModelScope.launch {
        musicUseCase.skipToNextTrack()
    }

    private fun onPrevTrackPressed() = viewModelScope.launch {
        musicUseCase.skipToPrevTrack()
    }

    private fun onPlayPauseButtonPressed() = viewModelScope.launch {
        currentSong.value?.let {
            musicUseCase.playPause(it.getMusic().id, true)
        }
    }

    private fun collectPlaybackState() = viewModelScope.launch {
        playbackState.collectLatest { playback ->
            playback?.let {
                _uiState.value = uiState.value.copy(
                    isPlaying = it.isPlaying,
                    musicControlButtonState = uiState.value.musicControlButtonState.copy(
                        isPlaying = it.isPlaying

                    )
                )
            }
        }
    }

//    private fun collectTimePassed() = viewModelScope.launch {
//        musicUseCase.timePassed.collectLatest {
//            updateDurationInUI(it, uiState.value.totalDuration)
//        }
//    }
    private fun collectTimePassed() = viewModelScope.launch {
        musicUseCase.timePassed.collectLatest { currentTime ->
            val totalTime = uiState.value.totalDuration
            val canLooped = _musicControlButtonState.value.canLoop

            Log.d("SUCCESSSSSSS", "Current Time: $currentTime, Total Time: $totalTime, Bool: $canLooped")
            updateDurationInUI(currentTime, totalTime)

            if (currentTime > totalTime-1000 && canLooped == true) {
                musicUseCase.skipToPrevTrack()
                isLoopAvailable = canLooped
            }
        }
    }


    private fun collectSongs() = viewModelScope.launch {
        songs.collectLatest {
            val oldCount = uiState.value.songsCount
            _uiState.value = uiState.value.copy(allSongs = it)
            if (oldCount == 0 && it.isNotEmpty()) {
                collectCurrentSong()
            }
        }
    }

    private fun collectCurrentSong() = viewModelScope.launch {
        currentSong.collectLatest { song ->
            song?.let { metadata ->
                val music = metadata.getMusic()
//                fetchLyrics(music)
                val songIndex = songs.value.indexOf(music)
                _uiState.value = uiState.value.copy(
                    image = music.imageUrl,
                    songName = music.title,
                    songArtistName = music.artists.getArtistsString(),
                    currentSongIndex = if (songIndex != -1) songIndex else 0,
                    totalDuration = music.duration,
                    lyrics = music.lyrics,
                    musicControlButtonState = uiState.value.musicControlButtonState.copy(
                        isSkipNextButtonEnabled = songs.value.indexOfFirst { it.id == music.id } != songs.value.size - 1,
                        isSkipPrevButtonEnabled = songs.value.indexOfFirst { it.id == music.id } != 0
                    )
                )
            }
        }
    }


    private fun updateDurationInUI(currentTime: Long, totalTime: Long) {
        if (totalTime == 0L) return
        _uiState.value = uiState.value.copy(
            musicSliderState = uiState.value.musicSliderState.copy(
                timePassed = currentTime,
                timeLeft = totalTime - currentTime,
                sliderValue = currentTime / totalTime.toFloat()
            )
        )
    }


}
