package com.example.btl_mobile_spotify.screens.playlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.data.repo.MusicRepo
import com.example.btl_mobile_spotify.exoplayer.MusicServiceConnection
import com.example.btl_mobile_spotify.screens.libraryscreen.LibraryScreenState
import com.example.btl_mobile_spotify.utils.Dispatcher
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val musicRepo: MusicRepo,
    private val dispatcher: Dispatcher,
): ViewModel() {
    val user = Firebase.auth.currentUser
    private val _uiState = mutableStateOf(PlaylistScreenState())
    val uiState: State<PlaylistScreenState> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    private val playlistList = searchQuery.flatMapLatest {
        musicRepo.getAllPlaylistsFlow(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        collectAllPlaylist()
    }
    fun collectAllPlaylist() = viewModelScope.launch(dispatcher.main) {
        musicRepo.fetchAllPlaylist().collectLatest {  }
        playlistList.collectLatest {
            val tempList = it.filter { playlist -> playlist.userID == user?.uid  }
            _uiState.value = uiState.value.copy(playlistList = tempList)
        }
    }

    fun uploadPlaylist(playlist: Playlist) = viewModelScope.launch(dispatcher.main) {
        musicRepo.uploadPlaylist(playlist).collectLatest {
            Timber.d("${playlist.name} - ${it.javaClass}")
        }
    }
}