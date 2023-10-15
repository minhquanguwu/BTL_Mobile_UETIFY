package com.example.btl_mobile_spotify.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation


@Composable
fun ImageCrop(
    modifier: Modifier = Modifier,
    data: Any? = null,
    placeHolderRes: Int? = null,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
) {
    val painter = rememberImagePainter(
        data = data,
        builder = {
            transformations(CircleCropTransformation())
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment
    )
}