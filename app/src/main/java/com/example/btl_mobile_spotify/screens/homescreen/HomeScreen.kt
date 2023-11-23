package com.example.btl_mobile_spotify.screens.homescreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.components.IconBtn
import com.example.btl_mobile_spotify.components.MusicItem
import com.example.btl_mobile_spotify.components.TextTitle
import com.example.btl_mobile_spotify.components.TopAppBar
import com.example.btl_mobile_spotify.components.TopBar
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.navigation.Router
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(paddingValues: PaddingValues = PaddingValues(), router: Router? = null,
               viewModel: HomeViewModel = hiltViewModel(),) {
    val uiState by viewModel.uiState
    val musicList : List<Music> = uiState.musicList
    Log.d("Song","${musicList.size}")

    Column {
        TopAppBar(modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
        val scrollState = rememberLazyListState()
        LazyColumn(
            state = scrollState
        ){
            items(musicList) { music ->
                MusicItem(music = music, onItemClick = {viewModel.onMusicListItemPressed(it)})

            }

        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}