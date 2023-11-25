package com.example.btl_mobile_spotify.components

import Sizes.DEFAULT
import Sizes.SMALL
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.navigation.Screen

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable (RowScope.() -> Unit)?= null,
    actions: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = verticalAlignment
    ) {
        navigationIcon?.invoke()
        title?.invoke(this)
        actions?.invoke(this)
    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        navigationIcon = {
            IconBtn(resIcon = R.drawable.ic_down)
        },
        title = {
            TextTitle(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "title",
            )
        }
    )
}

@Composable
fun TopAppBar(
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
//        Button(onClick = { navController.navigate(Screen.Profile.route) }) {
//            Text(text = "CLICK")
//        }
        IconBtn(resIcon = R.drawable.ic_history) {
//            onActionClicked()
        }
        IconBtn(resIcon = R.drawable.ic_settings) {
            onActionClicked()
        }
    }
}

//@Preview
//@Composable
//fun TopAppBarPreview() {
//    TopAppBar()
//}