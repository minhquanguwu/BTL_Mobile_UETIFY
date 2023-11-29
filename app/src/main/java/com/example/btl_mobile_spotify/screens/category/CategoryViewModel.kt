package com.example.btl_mobile_spotify.screens.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.repo.MusicRepo
import com.example.btl_mobile_spotify.utils.Dispatcher
import com.example.btl_mobile_spotify.utils.MusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val musicRepo: MusicRepo,
    private val musicUseCase: MusicUseCase,
    private val dispatcher: Dispatcher
): ViewModel() {
    init {
        collectSongs()
    }

    private val _uiState = mutableStateOf(CategoryScreenState())
    val uiState: State<CategoryScreenState> = _uiState

    private val _genre  = MutableStateFlow("")
    val genre = _genre.asStateFlow()

    private val musicListByGenre = genre.flatMapLatest {
        musicRepo.getSongByGenre(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private fun collectSongs() = viewModelScope.launch(dispatcher.main) {
        musicRepo.fetchAllMusic()
        musicListByGenre.collectLatest {
            _uiState.value = uiState.value.copy(musicListByGenre = it)
        }
    }

    fun onGenreChange(genre: String) {
        _genre.value = genre
    }

    fun onMusicListItemPressed(music: Music) = viewModelScope.launch(dispatcher.main) {
        musicUseCase.playPause(music.id)
    }
}