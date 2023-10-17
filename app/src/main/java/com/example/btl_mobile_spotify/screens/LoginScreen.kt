package com.example.btl_mobile_spotify.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import com.example.btl_mobile_spotify.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.btl_mobile_spotify.navigation.Graph

@Composable
fun LoginScreen(navController: NavHostController){
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .width(428.dp)
                .height(926.dp)
                .background(color = Color(0xFF121212))
        ) {
            Column(
                //horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 0.dp, vertical = 0.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 31.dp, vertical = 42.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {},
                        shape = CircleShape,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF000000))
                    ) {
                        Image(
                            painterResource(id = R.drawable.back_arrow),
                            contentDescription = "Cart button icon",
                            modifier = Modifier.width(40.dp).height(40.dp)
                        )
                    }

                    Text(
                        text = "Log in",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .padding(end = 35.dp)
                            .fillMaxWidth().paddingFromBaseline(16.dp)
                    )
                }
                Text(
                    text = "Enter your Email or Username",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start,
                    ),
                    modifier = Modifier
                        .width(300.dp)
                        .height(27.dp).padding(start = 31.dp)
                )
                TextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    //label = { Text(text = "Email or Username") },
                    placeholder = {
                        Text(text = "Email or Username",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color(0xFFD6D6D6),
                                textAlign = TextAlign.Start,
                        ))},
                    modifier = Modifier
                        .width(350.dp)
                        .height(50.dp).padding(start = 31.dp)
                        .background(color = Color(0xFF777777),
                            shape = RoundedCornerShape(size = 5.dp)
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Enter your password",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start,
                    ),
                    modifier = Modifier
                        .width(300.dp)
                        .height(27.dp).padding(start = 31.dp)
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    placeholder = {
                        Text(text = "Password",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color(0xFFD6D6D6),
                                textAlign = TextAlign.Start,
                            ))},
                    modifier = Modifier
                        .width(350.dp)
                        .height(50.dp).padding(start = 31.dp)
                        .background(color = Color(0xFF777777),
                            shape = RoundedCornerShape(size = 5.dp))
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {navController.navigate(Graph.CONTENT)},
                    modifier = Modifier
                        .width(150.dp)
                        .height(49.dp).align(CenterHorizontally),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF777777)),
                    border = BorderStroke(1.dp, Color.White),
                ) {
                    Text(
                        text = "Log in",
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
            }
        }
    }
}

//@Preview
//@Composable
//fun LoginScreen2Preview(){
//    LoginScreen1()
//}