package com.example.btl_mobile_spotify.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SearchViewModel: ViewModel() {
    private val _genres = MutableStateFlow(allGenres)
    val genres = _genres.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _songs = MutableStateFlow(allSongs)

    val songs = searchQuery.combine(_songs) { query, songs ->
        if(query.isBlank()) {
            songs
        } else {
            songs.filter {
                it.isMatchingQuery(query)
            }
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _songs.value)

    fun onSearchTextChange(query: String) {
        _searchQuery.value = query
    }
}

data class Song(
    val title: String,
) {
    fun isMatchingQuery(query: String): Boolean {
//        val matchingCombination = listOf()
        return title.contains(query, ignoreCase = true)
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

private val allSongs = listOf(
    Song(title="Anh Đã Ổn Hơn"),
    Song(title="Có Em"),
    Song(title="comethru"),
    Song(title="Demons")
)