package com.example.btl_mobile_spotify.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
import com.example.btl_mobile_spotify.utils.getArtistsString
import com.example.btl_mobile_spotify.utils.sampleMusicList
import kotlinx.coroutines.Job


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicItem(
    music: Music,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.background,
    shape: Shape = RoundedCornerShape(8.dp),
    onItemClick: (Music) -> Unit,
) {
    Card(
        onClick = { onItemClick(music) },
        shape = shape,
        modifier = modifier,
        backgroundColor = color,
        elevation = 1.dp,
    ) {
        var showMenu by remember { mutableStateOf(false)}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(music.imageUrl),
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
                    text = music.title,
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = music.artists.getArtistsString(),
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                IconBtn(resIcon = R.drawable.ic_dots, onClick = {showMenu = !showMenu})
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(onClick = {

                    }) {
                        Text("Add to playlist", style = MaterialTheme.typography.body1)
                    }
                    DropdownMenuItem(onClick = { /*TODO*/ }) {
                        Text("Add to favourite", style = MaterialTheme.typography.body1)
                    }
                }
            }
        }
    }
}


// Previews
@Preview
@Composable
private fun MusicItemPreview() {
    BTL_Mobile_SpotifyTheme {
        MusicItem(
            music = sampleMusicList[0],
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            color = MaterialTheme.colors.surface,
            onItemClick = {},
        )
    }
}

