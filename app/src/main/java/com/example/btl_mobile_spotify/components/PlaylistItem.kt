//package com.example.btl_mobile_spotify.components
//
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Card
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import coil.compose.rememberImagePainter
//import com.example.btl_mobile_spotify.R
//import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme
//
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun PlaylistItem(
//    playlists: Playlist,
//    modifier: Modifier = Modifier,
//    color: Color = MaterialTheme.colors.background,
//    shape: Shape = RoundedCornerShape(8.dp),
//    onItemClick: (Playlist) -> Unit
//) {
//    Card(
//        onClick = { onItemClick(playlists) },
//        shape = shape,
//        modifier = modifier,
//        backgroundColor = color,
//        elevation = 1.dp,
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = rememberImagePainter(playlists.imageUrl),
//                contentDescription = "Playlist cover",
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(shape),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(250.dp),
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = playlists.title,
//                    style = MaterialTheme.typography.h6,
//                    color = Color.White,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//            Spacer(modifier = Modifier.weight(1f))
//            IconBtn(resIcon = R.drawable.ic_dots)
//        }
//    }
//}
//
//
//// Previews
//
