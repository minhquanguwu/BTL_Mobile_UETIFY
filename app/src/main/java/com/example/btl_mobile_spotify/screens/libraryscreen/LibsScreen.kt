package com.example.btl_mobile_spotify.screens.libraryscreen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.components.ActivityItemData
import com.example.btl_mobile_spotify.components.HeaderSection
import com.example.btl_mobile_spotify.components.TextTitle
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.data.models.local.Playlist
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.ui.theme.DarkBackground
import com.example.btl_mobile_spotify.ui.theme.Silver
import com.example.btl_mobile_spotify.ui.theme.albumItemDarkColor
import com.example.btl_mobile_spotify.ui.theme.albumItemLightColor
import com.example.btl_mobile_spotify.ui.theme.albumItemMediumColor
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
//    LazyColumn() {
//        items(playlistList) { playlist ->
//            Row() {
//                Text(text = "${playlist.id} ${playlist.name} ")
//            }
//        }
//    }
//    Button(onClick = {
//        val playlist = Playlist(id = UUID.randomUUID().toString(), name = "Quang", userID = user?.uid.toString())
//        viewModel.uploadPlaylist(playlist)
//    }) {
//        Text(text = "Add")
//    }
    Column(
        modifier = Modifier
            .background(DarkBackground)
            .fillMaxSize()
            .padding(vertical = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderSection("Library", Modifier.padding(horizontal = 32.dp))
        SubtitleSection("Quick Menu")
        AlbumSection(router)
        SubtitleSection("Your Activities")
        ActivitySection(router)
    }
}

@Composable
private fun SubtitleSection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.body1,
        color = Color.White,
//        fontWeight = FontWeight(FontWeight.Thin.weight),
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
    )
}

@Composable
private fun AlbumSection(router: Router? = null) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {

            AlbumItem(painterResource(id = R.drawable.ic_playlist), "Playlists", "23 Songs",router)
            AlbumItem(painterResource(id = R.drawable.ic_mp3), "Mp3s", "3 Songs")
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            AlbumItem(painterResource(id = R.drawable.ic_video), "Videos", "No Videos")
            AlbumItem(painterResource(id = R.drawable.ic_album), "Albums", "7 Songs")
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
private fun AlbumItem(
    icon: Painter,
    title: String,
    subtitle: String,
    router: Router? =null

) {
    ConstraintLayout(modifier = Modifier.padding(vertical = 4.dp)) {

        val (card, canvas) = createRefs()
        val middleGuideline = createGuidelineFromEnd(80.dp)

        Canvas(
            modifier = Modifier
                .constrainAs(canvas) {
                    end.linkTo(middleGuideline)
                    top.linkTo(parent.top)
                }

        ) {
            drawRoundRect(
                color = albumItemDarkColor,
                size = Size(160f, 50f),
                cornerRadius = CornerRadius(16f)
            )
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(top = 8.dp, bottom = 8.dp)
                .aspectRatio(1f)
                .clickable { router?.goAddToPlaylistScreen(null) }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                albumItemLightColor,
                                albumItemMediumColor
                            )
                        )
                    )
            ) {
                Icon(
                    painter = icon,
                    contentDescription = "Playlist",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(albumItemDarkColor)
                        .padding(8.dp)
                        .size(32.dp),
                    tint = Silver
                )
                Text(
                    text = title,
                    color = Silver,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = subtitle,
                    color = Silver,
                    fontWeight = FontWeight(FontWeight.Thin.weight),
                    style = MaterialTheme.typography.caption,
                    fontSize = TextUnit(10f, TextUnitType.Sp),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun ActivitySection(router: Router?) {
    activityList().run {
        forEachIndexed { index, item ->
            ActivityItem(icon = item.icon, title = item.title, router = router)
            if (index != lastIndex) {
                Divider(
                    color = Silver.copy(alpha = 0.12f),
                    thickness = 1.dp,
                    startIndent = 64.dp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ActivityItem(icon: Int, title: String,router: Router? = null) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 32.dp)
            .clickable {
                router?.goAddToPlaylistScreen(null)
            }

    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Silver
        )
        Text(
            text = title,
            color = Silver,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_right_chevron),
            contentDescription = title,
            tint = Silver,
            modifier = Modifier.size(12.dp)
        )
    }
}

private fun activityList(): List<ActivityItemData> {
    return listOf(
        ActivityItemData("Your Playlists", R.drawable.ic_playlist ),
        ActivityItemData("Liked Songs", R.drawable.ic_like),
        ActivityItemData("Followed Artists", R.drawable.ic_artist),
        ActivityItemData("History", R.drawable.ic_history)
    )
}