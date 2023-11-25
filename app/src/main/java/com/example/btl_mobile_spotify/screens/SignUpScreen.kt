package com.example.btl_mobile_spotify.screens


import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.btl_mobile_spotify.navigation.Graph
import com.example.btl_mobile_spotify.navigation.Screen
import com.example.btl_mobile_spotify.screens.sign_in.native_method.NativeLoginUIEvent
import com.example.btl_mobile_spotify.screens.sign_in.native_method.NativeLoginViewModel
import com.example.btl_mobile_spotify.screens.sign_up.SignUpUIEvent
import com.example.btl_mobile_spotify.screens.sign_up.SignUpUIState
import com.example.btl_mobile_spotify.screens.sign_up.SignUpViewModel

@Composable
fun SignUpScreen(navController: NavHostController,
                signUpViewModel: SignUpViewModel,
                 onToggleBottomBar: (Boolean) -> Unit
){
    onToggleBottomBar(false)
    var name by remember { mutableStateOf(("")) }
    var email by remember { mutableStateOf(("")) }
    var password by remember { mutableStateOf(("")) }
    val context = LocalContext.current
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
                        onClick = {
                            navController.navigate(Screen.Start.route)
                                  },
                        shape = CircleShape,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF000000))
                    ) {
                        Image(
                            painterResource(id = R.drawable.back_arrow),
                            contentDescription = "Cart button icon",
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                        )
                    }

                    Text(
                        text = "Sign up",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .padding(end = 35.dp)
                            .fillMaxWidth()
                            .paddingFromBaseline(16.dp)
                    )
                }
                Text(
                    text = "What's your name?",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start,
                    ),
                    modifier = Modifier
                        .width(300.dp)
                        .height(27.dp)
                        .padding(start = 31.dp)
                )
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        signUpViewModel.onEvent(SignUpUIEvent.NameChanged(it))
                    },
                    //label = { Text(text = "Email or Username") },
                    placeholder = {
                        Text(text = "Name",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color(0xFFD6D6D6),
                                textAlign = TextAlign.Start,
                            ))},
                    modifier = Modifier
                        .width(350.dp)
                        .height(50.dp)
                        .padding(start = 31.dp)
                        .background(
                            color = Color(0xFF777777),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "What's your email?",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start,
                    ),
                    modifier = Modifier
                        .width(300.dp)
                        .height(27.dp)
                        .padding(start = 31.dp)
                )
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                        signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                    },
                    //label = { Text(text = "Email or Username") },
                    placeholder = {
                        Text(text = "Email",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color(0xFFD6D6D6),
                                textAlign = TextAlign.Start,
                            ))},
                    modifier = Modifier
                        .width(350.dp)
                        .height(50.dp)
                        .padding(start = 31.dp)
                        .background(
                            color = Color(0xFF777777),
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
                        .height(27.dp)
                        .padding(start = 31.dp)
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
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
                        .height(50.dp)
                        .padding(start = 31.dp)
                        .background(
                            color = Color(0xFF777777),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                )
                Spacer(modifier = Modifier.height(24.dp))

                if (signUpViewModel.isSignedUpFail.value == true) {
                    Text(
                        text = "Invalid email or password",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            color = Color.Red, // You can customize the color
                            textAlign = TextAlign.Start,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 31.dp)
                    )
                }

                Button(
                    onClick ={
                        signUpViewModel.onEvent(SignUpUIEvent.SignUpButtonClicked)
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(49.dp)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF777777)),
                    border = BorderStroke(1.dp, Color.White),
                ) {
                    Text(
                        text = "Sign up",
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
//    SignUpScreen(navController = rememberNavController() )
//}