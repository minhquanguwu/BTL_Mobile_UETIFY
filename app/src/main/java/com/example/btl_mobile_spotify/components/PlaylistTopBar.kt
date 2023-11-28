package com.example.btl_mobile_spotify.components

import Sizes.DEFAULT
import Sizes.SMALL
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.btl_mobile_spotify.R
@Composable
fun PlaylistTopBar(
    modifier: Modifier = Modifier,
    title: String = "Latest Tracks",
    onActionClicked: ()  -> Unit,

    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = SMALL),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextTitle(
            modifier = Modifier
                .padding(horizontal = DEFAULT)
                .weight(1f),
            text = title,
        )
        IconBtn(resIcon = R.drawable.add_icon) {
            onActionClicked()
        }
    }
}