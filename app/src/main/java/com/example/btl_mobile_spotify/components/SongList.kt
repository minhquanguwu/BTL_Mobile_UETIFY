package com.example.btl_mobile_spotify.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.screens.homescreen.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SongList(viewModel : HomeViewModel, musicList : List<Music>) {

    var sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val lifecycleScope = rememberCoroutineScope()
    var tempMusic by remember {
        mutableStateOf(Music())
    }

    BackHandler(sheetState.isVisible) {
        lifecycleScope.launch {
            sheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Color(0, 0, 0, 146),
        sheetState = sheetState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .height(400.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextTitle("Add ${tempMusic.title}" )
                    Divider(modifier = Modifier.fillMaxWidth(), color = Color.White, thickness = 3.dp)
                    viewModel.uiState.value.playlistList.forEach { playlist ->
                        var checked = playlist.listMusicId.contains(tempMusic.id)
                        Log.d("Checkbox", "${playlist.listMusicId.contains(tempMusic.id)} ${tempMusic.id} ${playlist.listMusicId}")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(250.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = playlist.name,
                                    style = MaterialTheme.typography.h6,
                                    color = Color.White,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Checkbox(
                                colors = CheckboxDefaults.colors(uncheckedColor = Color.DarkGray, checkedColor = Color.White),
                                checked = checked, onCheckedChange = {checkValue ->
                                    checked = checkValue
                                    if (checked) {
                                        if (!playlist.listMusicId.contains(tempMusic.id)) {
                                            val updatedList = playlist.listMusicId.toMutableList().apply {
                                                add(tempMusic.id)
                                            }
                                            val updatedPlaylist = playlist.copy(listMusicId = updatedList)
                                            viewModel.addSongToPlaylist(tempMusic, playlist)
                                            viewModel.uploadPlaylist(updatedPlaylist)
                                        }
                                    } else {
                                        if (playlist.listMusicId.contains(tempMusic.id)) {
                                            val updatedList = playlist.listMusicId.toMutableList().apply {
                                                remove(tempMusic.id)
                                            }
                                            val updatedPlaylist = playlist.copy(listMusicId = updatedList)
                                            viewModel.uploadPlaylist(updatedPlaylist)
                                        }

                                    }
                                }, modifier = Modifier.weight(1f))
                        }
                    }
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomCenter)) {
                    TextTitle("OK")
                }
            }
        },
        content = {
            val scrollState = rememberLazyListState()
            Column {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    state = scrollState
                ){
                    items(musicList) { music ->
                        MusicItemNew(music = music, onItemClick = {viewModel.onMusicListItemPressed(it);}) {
                            tempMusic = music
                            lifecycleScope.launch {
                                sheetState.show()
                            }
                        }
                    }
                }
            }
        }
    )

}