package com.example.btl_mobile_spotify.screens.playlist

import Sizes.DEFAULT
import Sizes.SMALL
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.components.IconBtn
import com.example.btl_mobile_spotify.components.MusicItem
import com.example.btl_mobile_spotify.components.TextTitle
import com.example.btl_mobile_spotify.components.TopAppBar
import com.example.btl_mobile_spotify.components.TopBar
import com.example.btl_mobile_spotify.navigation.Router

@Composable
fun ListSongInPlaylistScreen(router: Router, viewModel: PlaylistViewModel = hiltViewModel()) {
    val musicList = viewModel.uiState.value.currentMusicList
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            ListSongTopBar(
                title = "${viewModel.uiState.value.currentPlaylist.name}"
            ) {}
            val scrollState = rememberLazyListState()
            LazyColumn(
                state = scrollState
            ) {
                items(musicList) { music ->
                    MusicItem(
                        music = music,
                        onItemClick = {viewModel.onMusicListItemPressed(it)
                        })
                }
            }
        }
    }
}

@Composable
fun ListSongTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onActionClicked: () -> Unit
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = SMALL),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextTitle(
            modifier = Modifier
                .padding(horizontal = DEFAULT)
                .weight(1f),
            text = title,
        )
        IconBtn(resIcon = R.drawable.add_icon) {
            onActionClicked()
        }
    }
}