package com.example.btl_mobile_spotify.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.components.MusicItem
import kotlin.random.Random

@Composable
fun CategoryScreen(genre: String?, viewModel: CategoryViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState
    val songs = uiState.musicListByGenre
    viewModel.onGenreChange(genre.toString())

    val randomColor = Color(
        red = Random.nextInt(0, 255),
        green = Random.nextInt(0, 255),
        blue = Random.nextInt(0, 255)
    )

    Column {
        Card (
            backgroundColor = randomColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = genre.toString(),
                    modifier = Modifier.padding(5.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (ColorUtils.calculateLuminance(randomColor.toArgb()) < 0.5) Color.White else Color.Black
                )
            }
        }
        LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(songs) { song ->
                    MusicItem(music = song, onItemClick = {viewModel.onMusicListItemPressed(it)})
                }
            }
    }
}