package com.example.btl_mobile_spotify.components

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.screens.searchscreen.SearchViewModel
import com.example.btl_mobile_spotify.ui.theme.BTL_Mobile_SpotifyTheme

@Composable
fun SearchBar(
    interactionSource: MutableInteractionSource,
    hasTrailingIcon: Boolean = true
    ) {
    val viewModel = viewModel<SearchViewModel>()
    val searchText by viewModel.searchQuery.collectAsState()

    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            //TODO: Record audio
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Row(
        Modifier.height(IntrinsicSize.Min)
    ) {
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.DarkGray
            ),
            value = if(isFocused) searchText else "",
            onValueChange = viewModel::onSearchTextChange,
            interactionSource = interactionSource,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Songs, albums or artists") },
            leadingIcon = {
                if(isFocused) {
                    IconButton(onClick = { focusManager.clearFocus(true) }) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
                    }
                } else {
                    Icon(Icons.Outlined.Search, contentDescription = "Search")
                }
            },
            trailingIcon = {
                if (hasTrailingIcon) {
                    if(isFocused) {
                        IconButton(onClick = { viewModel.clearSearchText() }) {
                            Icon(Icons.Outlined.Clear, contentDescription = "Clear text")
                        }
                    }
                    else {
                        IconBtn(
                            resIcon = R.drawable.ic_shazam,
                            tint = Color.Unspecified,
                            onClick = {
                                when (PackageManager.PERMISSION_GRANTED) {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.RECORD_AUDIO
                                    ) -> {
                                        //TODO: Record audio
                                    }
                                    else -> {
                                        launcher.launch(Manifest.permission.RECORD_AUDIO)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    BTL_Mobile_SpotifyTheme {
        val interactionSource = remember { MutableInteractionSource() }
        SearchBar(interactionSource, true)
    }
}