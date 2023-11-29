package com.example.btl_mobile_spotify.screens.playlist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.components.IconBtn
import com.example.btl_mobile_spotify.components.PlaylistTopBar
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.data.models.remote.MusicDTO
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.utils.getArtistsString
import java.util.UUID

@Composable
fun PlaylistScreen(viewModel: PlaylistViewModel = hiltViewModel(), router : Router) {
    var showDialog by remember{mutableStateOf(false)}
    val scrollState = rememberLazyListState()
    val uiState by viewModel.uiState
    val playlistList : List<Playlist> = uiState.playlistList

    Column {
        PlaylistTopBar(
            title = "Your Playlist",
            onActionClicked = { showDialog = !showDialog }
        )
        if(showDialog) {
            CreateAlbumDialog(onDialogDismiss = { showDialog =!showDialog },
                onCreateAlbum = {
                    val playlist = Playlist(name = it, id = UUID.randomUUID().toString(), userID = viewModel.user?.uid.toString(), listMusicId = emptyList())
                    viewModel.uploadPlaylist(playlist)
                    viewModel.collectAllPlaylist()
                    showDialog =!showDialog
            })
        }
        LazyColumn(state = scrollState) {
            items(playlistList) {playlist ->
                PlaylistItem(playlist) {
                    viewModel.getCurrentPlaylist(playlist)
                    router.goListSongInPlaylistScreen(playlist)

                }
            }

        }
    }
}


@Composable
fun CreateAlbumDialog(
    onDialogDismiss: () -> Unit,
    onCreateAlbum: (String) -> Unit
) {
        var albumName by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = {
                onDialogDismiss()
            },
            title = {
                Text("Create New Album")
            },
            text = {
                Column {
                    Text("Enter the name of the new album:")
                    OutlinedTextField(
                        value = albumName,
                        onValueChange = { albumName = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        label = { Text("Album Name") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onCreateAlbum(albumName)
                    }
                ) {
                    Text("Create")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        //dismiss the dialog when the user clicks on the dismiss button
                        onDialogDismiss()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaylistItem(
    playlist: Playlist,
    isShow : Boolean = false,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.background,
    shape: Shape = RoundedCornerShape(8.dp),
    onItemClick: () -> Unit)
{
    Card(
        onClick = { onItemClick() },
        shape = shape,
        modifier = modifier,
        backgroundColor = color,
        elevation = 1.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(R.drawable.album),
                contentDescription = "Music cover",
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
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

        }
    }
}

