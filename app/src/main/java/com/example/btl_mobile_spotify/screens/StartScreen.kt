package com.example.btl_mobile_spotify.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import com.example.btl_mobile_spotify.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.btl_mobile_spotify.navigation.AuthScreen
import com.example.btl_mobile_spotify.navigation.Graph
import com.example.btl_mobile_spotify.navigation.Router
import com.example.btl_mobile_spotify.navigation.Screen

@Composable
fun StartScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .width(428.dp)
                .height(926.dp)
                .background(color = Color(0xFF121212))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 0.dp, vertical = 0.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.start_img),
                    contentDescription = "image description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(428.dp)
                        .height(465.dp)
                )
                Text(
                    modifier = Modifier
                        .width(246.dp)
                        .height(84.dp),
                    text = "Millions of Songs.\nFree on Spotify.",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(337.dp)
                        .height(49.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1ED760))
                ) {
                    Text(
                        text = "Sign up free",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .width(337.dp)
                            .height(23.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(337.dp)
                        .height(49.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF121212)),
                    border = BorderStroke(1.dp, Color.White),
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_google),
                        contentDescription ="google button icon",
                        modifier = Modifier.size(20.dp))
                    Text(
                        text = "Continue with Google",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFF5F5F5),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .width(337.dp)
                            .height(23.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        navController.navigate(AuthScreen.SignUp.route)
                    },
                    modifier = Modifier
                        .width(337.dp)
                        .height(49.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF121212)),
                    border = BorderStroke(1.dp, Color.White),
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_facebook),
                        contentDescription ="google button icon",
                        modifier = Modifier.size(20.dp))
                    Text(
                        text = "Continue with Facebook",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFF5F5F5),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .width(337.dp)
                            .height(23.dp)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Graph.AUTHENTICATION)
                              },
                    modifier = Modifier
                        .width(100.dp)
                        .height(38.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF121212)),
                ) {
                    Text(
                        text = "Log in",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFF5F5F5),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .width(61.dp)
                            .height(23.dp)
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun LoginScreen1Preview() {
//    StartScreen(paddingValues = )
//}