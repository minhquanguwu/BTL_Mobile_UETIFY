package com.example.btl_mobile_spotify.screens.playlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.btl_mobile_spotify.components.PlaylistTopBar
import com.example.btl_mobile_spotify.data.models.remote.MusicDTO

@Composable
fun PlaylistScreen(
) {
    var showDialog by remember{mutableStateOf(false)}
    
    Row {

        PlaylistTopBar(
            title = "Your Playlist",
            onActionClicked = { showDialog = !showDialog }
        )
        if(showDialog) {
            CreateAlbumDialog(onDialogDismiss = { showDialog =!showDialog },
                onCreateAlbum = {

            })
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



