package com.example.btl_mobile_spotify.screens.libraryscreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.navigation.Router
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.UUID

@Composable
fun LibsScreen(paddingValues: PaddingValues = PaddingValues(), router: Router?, viewModel: LibraryViewModel = hiltViewModel()) {
    val user = Firebase.auth.currentUser
    val uiState by viewModel.uiState
    val playlistList : List<Playlist> = uiState.playlistList
    LazyColumn() {
        items(playlistList) { playlist ->
            Row() {
                Text(text = "${playlist.id} ${playlist.name} ")
            }
        }
    }
    Button(onClick = {
        val playlist = Playlist(id = UUID.randomUUID().toString(), name = "Quang", userID = user?.uid.toString())
        viewModel.uploadPlaylist(playlist)
    }) {
        Text(text = "Add")
    }

}