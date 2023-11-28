package com.example.btl_mobile_spotify.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.screens.sign_in.additional_method.UserData
import com.example.btl_mobile_spotify.screens.sign_in.native_method.NativeLoginViewModel
import com.example.btl_mobile_spotify.screens.user_data.UserDataViewModel
import com.example.btl_mobile_spotify.screens.user_data.UserInfo
import com.example.btl_mobile_spotify.utils.Ext.color

@Composable
fun ProfileScreen(
    userData: UserData?,
    userInfo: UserInfo?,
    onSignOut: () -> Unit,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            AsyncImage(
                model = "https://as1.ftcdn.net/v2/jpg/05/34/22/36/1000_F_534223627_0JFVJDBwNku7LyLazrtN6YBTJ2agUfP5.webp",
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if(userInfo?.username != null) {
            Text(
                text = userInfo.username,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if(userInfo?.email != null) {
            Text(
                text = userInfo.email,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onSignOut, colors = ButtonDefaults.buttonColors(Color.Green)) {
            Text(text = "Sign out", color = Color.Black)
        }
        Button(onClick = {navController.popBackStack()}, colors = ButtonDefaults.buttonColors(Color.Green)) {
            Text(text = "Back", color = Color.Black)
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(userData = UserData("", "", ""), userInfo = UserInfo(), onSignOut = { /*TODO*/ }, navController = rememberNavController())
}

