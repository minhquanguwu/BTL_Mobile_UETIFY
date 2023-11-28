package com.example.btl_mobile_spotify.screens.searchscreen

import android.util.Log
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
class SearchViewModel @Inject constructor(
    private val musicRepo: MusicRepo,
    private val musicUseCase: MusicUseCase,
    private val dispatcher: Dispatcher
): ViewModel() {
    init {
        collectSongs()

    }

    private val _uiState = mutableStateOf(SearchScreenState())
    val uiState: State<SearchScreenState> = _uiState

    private val _genres = MutableStateFlow(allGenres)
    val genres = _genres.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val musicList = searchQuery.flatMapLatest {
        musicRepo.getAllSongsFlow(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun onSearchTextChange(query: String) {
        _searchQuery.value = query
    }

    fun onMusicListItemPressed(music: Music) = viewModelScope.launch(dispatcher.main) {
        musicUseCase.playPause(music.id)
    }

    private fun collectSongs() = viewModelScope.launch(dispatcher.main) {
        musicRepo.fetchAllMusic()
        musicList.collectLatest {
            _uiState.value = uiState.value.copy(musicList = it)
        }
    }
}

private val allGenres = listOf(
    "Pop",
    "Dance/Electronic",
    "Rap",
    "Hip-Hop",
    "Rock",
    "Classical",
    "Indie",
    "Country"
)