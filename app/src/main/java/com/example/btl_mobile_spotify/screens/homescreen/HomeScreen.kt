package com.example.btl_mobile_spotify.screens.homescreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.btl_mobile_spotify.components.MusicItem
import com.example.btl_mobile_spotify.components.NavigationBar
import com.example.btl_mobile_spotify.components.TopAppBar
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.navigation.NavigationContainer
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.navigation.RouterImpl
import com.example.btl_mobile_spotify.navigation.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(
//            paddingValues: PaddingValues = PaddingValues(), router: Router? = null,
               navController: NavHostController, viewModel: HomeViewModel = hiltViewModel(),
               onToggleBottomBar: (Boolean) -> Unit) {
    onToggleBottomBar(true)
    val uiState by viewModel.uiState
    val musicList : List<Music> = uiState.musicList
    Log.d("Song","${musicList.size}")
    Column {
        TopAppBar(modifier = Modifier.padding(top = 4.dp, bottom = 8.dp), navController)
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

//@Preview (showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}