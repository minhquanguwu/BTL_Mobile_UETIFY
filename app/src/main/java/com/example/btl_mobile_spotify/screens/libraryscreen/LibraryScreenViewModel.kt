package com.example.btl_mobile_spotify.screens.libraryscreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.data.models.remote.PlaylistDTO
import com.example.btl_mobile_spotify.data.remote.FirebaseMusicDataSource
import com.example.btl_mobile_spotify.data.repo.MusicRepo
import com.example.btl_mobile_spotify.utils.Dispatcher
import com.example.btl_mobile_spotify.utils.Resource
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
class LibraryViewModel @Inject constructor(
    private val musicRepo: MusicRepo,
    private val dispatcher: Dispatcher,
) : ViewModel() {
    val user = Firebase.auth.currentUser
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _uiState = mutableStateOf(LibraryScreenState())
    val uiState: State<LibraryScreenState> = _uiState

    private val playlistList = searchQuery.flatMapLatest {
        musicRepo.getAllPlaylistsFlow(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val musicList = searchQuery.flatMapLatest {
        musicRepo.getAllSongsFlow(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    init {
//        samplePlaylist.subList(0, samplePlaylist.size).forEach {
//            uploadPlaylist(it)
//        }
        Log.d("User", "${user?.uid}")
        collectAllPlaylist()
    }


    private fun collectAllPlaylist() = viewModelScope.launch(dispatcher.main) {
        musicRepo.fetchAllPlaylist().collectLatest {  }
        playlistList.collectLatest {
            _uiState.value = uiState.value.copy(playlistList = it)
        }

    }

    fun uploadPlaylist(playlist: Playlist) = viewModelScope.launch(dispatcher.main) {
        musicRepo.uploadPlaylist(playlist).collectLatest {
            Timber.d("${playlist.name} - ${it.javaClass}")
        }
        var playlistList = arrayListOf<Playlist>()
        playlistList.add(playlist)
        musicRepo.insertPlaylists(playlistList)
    }

}