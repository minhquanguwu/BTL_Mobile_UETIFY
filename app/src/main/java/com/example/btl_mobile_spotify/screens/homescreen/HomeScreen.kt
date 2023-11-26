package com.example.btl_mobile_spotify.screens.homescreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.components.MusicBottomBar
import com.example.btl_mobile_spotify.components.MusicItem
import com.example.btl_mobile_spotify.components.TopAppBar
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.navigation.Router
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(paddingValues: PaddingValues = PaddingValues(), router: Router? = null, viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    val musicList : List<Music> = uiState.musicList
    Log.d("Song","${musicList.size}")

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TopAppBar(modifier = Modifier.padding(top = 4.dp, bottom = 8.dp), onActionClicked = {router?.goProfileScreen(null)})
            val scrollState = rememberLazyListState()
            LazyColumn(
                state = scrollState
            ){
                items(musicList) { music ->
                    MusicItem(music = music, onItemClick = {viewModel.onMusicListItemPressed(it); })
                }
            }
        }
        AnimatedVisibility(
            visible = uiState.isMusicBottomBarVisible && uiState.currentPlayingMusic != null,
//            enter = scaleIn(),
//            exit = scaleOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
//                .navigationBarsWithImePadding()
                .padding(start = 3.dp, end = 3.dp, bottom = 64.dp)
        ) {
            Log.d("paly", "${uiState.isMusicPlaying}")
            MusicBottomBar(
                music = uiState.currentPlayingMusic!!,
                isPlaying = uiState.isMusicPlaying,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                onPlayPauseButtonPressed = viewModel::onPlayPauseButtonPressed,
                onItemClick = {router?.goPlayerFull(null)}
            )
        }

    }

}

//@Preview (showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}