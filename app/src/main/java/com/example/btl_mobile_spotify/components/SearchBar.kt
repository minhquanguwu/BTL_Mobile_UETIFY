package com.example.btl_mobile_spotify.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.models.SearchViewModel
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme

@Composable
fun SearchBar(
    interactionSource: MutableInteractionSource,
    trailingIcon: @Composable (() -> Unit)? = {
        IconBtn(resIcon = R.drawable.ic_shazam, tint = Color.Unspecified)
    }
    ) {
    val viewModel = viewModel<SearchViewModel>()
    val searchText by viewModel.searchQuery.collectAsState()

    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    Row(
        Modifier.height(IntrinsicSize.Min)
    ) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            singleLine = true,
            interactionSource = interactionSource,
            modifier = Modifier.weight(1f),
            placeholder = { Text(text = "Songs, albums or artists") },
            leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Search") },
            trailingIcon = if (!isFocused) trailingIcon else null
        )
        if (isFocused) {
            Button(
                onClick = {
                    focusManager.clearFocus(true) },
                    modifier = Modifier.fillMaxHeight()
            ) {
                Text("Cancel")
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    BTL_Mobile_SpotifyTheme {
        val interactionSource = remember { MutableInteractionSource() }
        SearchBar(interactionSource)
    }
}