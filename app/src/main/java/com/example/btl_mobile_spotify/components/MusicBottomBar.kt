package com.example.btl_mobile_spotify.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
import com.example.btl_mobile_spotify.utils.getArtistsString
import com.example.btl_mobile_spotify.utils.sampleMusicList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicBottomBar(
    music: Music,
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    onPlayPauseButtonPressed: (Music) -> Unit
) {
    Surface(
        onClick = onItemClick,
//        tonalElevation = 4.dp,
//        shadowElevation = 4.dp,
        modifier = modifier
            .testTag("BOTTOM_BAR_TAG")
            .clickable { onItemClick },
        shape = RoundedCornerShape(12.dp)
    ) {
        Log.d("paly", "i have done")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            CoilImage(
                url = music.imageUrl,
                contentDescription = "MusicPoster",
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = music.title,
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = music.artists.getArtistsString(),
                    style = MaterialTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color.White
                )
            }
            PlayPauseButton(
                music = music,
                isPlaying = isPlaying,
                onPlayPauseButtonPressed = onPlayPauseButtonPressed
            )
        }
    }
}


@Composable
fun CoilImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0.dp)
) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(shape)
    )
}


@Preview
@Composable
fun MusicBottomBarPreviewDark() {
    BTL_Mobile_SpotifyTheme {
        MusicBottomBar(
            music = Music("", "Divide", 122131, listOf("Ed Sheeran"), "", "","",""),
            isPlaying = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            onItemClick = {}
        ) {
        }
    }
}
