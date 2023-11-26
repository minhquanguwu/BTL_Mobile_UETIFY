package com.example.btl_mobile_spotify.screens.searchscreen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.components.MusicItem
import com.example.btl_mobile_spotify.components.SearchBar
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
import kotlin.random.Random

@Composable
fun SearchScreen(paddingValues: PaddingValues = PaddingValues(), viewModel: SearchViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState
    val songs = uiState.musicList
    val genres by viewModel.genres.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }
    val searchInFocus by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(interactionSource)
        Spacer(Modifier.height(16.dp))

        if (searchInFocus) {
            SearchResults(songs)
        } else {
            Categories(genres)
        }
    }
}

@Composable
fun SearchResults(songs: List<Music>) {
    val viewModel: SearchViewModel = hiltViewModel()
    val isSearching by viewModel.isSearching.collectAsState()
    val searchText by viewModel.searchQuery.collectAsState()

    if (isSearching) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else if (searchText.isNotBlank()){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(songs) { song ->
                MusicItem(music = song, onItemClick = {viewModel.onMusicListItemPressed(it)})
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Categories(genres: List<String>) {
    Text(
        "Browse Categories",
        fontWeight = FontWeight.ExtraBold
    )
    Spacer(Modifier.height(16.dp))

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
    ) {
        items(genres) { genre ->
            val randomColor = Color(
                red = Random.nextInt(0, 255),
                green = Random.nextInt(0, 255),
                blue = Random.nextInt(0, 255)
            )
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .height(100.dp),
                onClick = {},
                backgroundColor = randomColor
            ) {
                Text(
                    text = genre,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    color = if (ColorUtils.calculateLuminance(randomColor.toArgb()) < 0.5) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
@Preview
fun SearchScreenPreview() {
    BTL_Mobile_SpotifyTheme {
        SearchScreen()
    }
}

