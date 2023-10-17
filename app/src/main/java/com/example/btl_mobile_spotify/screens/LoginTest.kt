package com.example.btl_mobile_spotify.screens

import android.util.Log
import androidx.compose.foundation.Image
import com.example.btl_mobile_spotify.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.btl_mobile_spotify.ui.theme.AppBlackColor
import com.example.btl_mobile_spotify.ui.theme.AppBlueColor
import com.example.btl_mobile_spotify.ui.theme.AppFocusColor
import com.example.btl_mobile_spotify.ui.theme.AppUnFocusedColor
import com.example.btl_mobile_spotify.ui.theme.FacebookButtonColor
import com.example.btl_mobile_spotify.ui.theme.GoogleButtonColor

@Composable
fun loginTest() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }

    val onSignInButtonClicked: (String, String) -> Unit = {email, password ->
        Log.i("LoginScreen", "Email: $email, Password: $password")
    }
    val onGoogleSignInButtonClicked: () -> Unit = {}
    val onFacebookSignInButtonClicked: () -> Unit = {}
    val onSignUpButtonClicked: () -> Unit = {}
    val onForgortPasswordButtonClicked: () -> Unit = {}
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(AppBlackColor)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_spotify),
                    contentDescription = "Sample Logo",
                    modifier = Modifier.padding(vertical = 24.dp)
                )
                Text (
                    text = stringResource(id = R.string.welcome),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 28.sp,
                    color = AppBlackColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.sign_in_continue),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    color = AppBlackColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = { Text(text = stringResource(id = R.string.label_email))},
                    placeholder = {Text(text = stringResource(id = R.string.hint_email))},
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = AppFocusColor,
                        unfocusedBorderColor = AppUnFocusedColor
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon",
                            tint = AppFocusColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                val visibilityIcon = if (passwordVisible)
                    Icons.Filled.ArrowBack
                else Icons.Filled.ArrowForward
                val description = if (passwordVisible) "Hide password" else "Show password"
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.label_password)) },
                    placeholder = { Text(text = stringResource(id = R.string.hint_password)) },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = AppFocusColor,
                        unfocusedBorderColor = AppUnFocusedColor
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Email Icon",
                            tint = AppFocusColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
//                    trailingIcon = {
//                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                            Icon(imageVector = visibilityIcon, description)
//                        }
//                    }
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it }
                        )
                        Text(
                            text = stringResource(id = R.string.remeber_me),
                            fontSize = 14.sp,
                            color = AppFocusColor
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        fontSize = 14.sp,
                        color = AppBlueColor,
                        modifier = Modifier
                            .clickable { onForgortPasswordButtonClicked()}
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { onSignInButtonClicked(email.text, password.text) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = AppBlueColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(Modifier.weight(0.5f))
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = stringResource(id = R.string.or_continue),
                        style = TextStyle(
                            color = AppFocusColor,
                            fontSize = 12.sp,
                        )
                    )
                    Spacer(Modifier.width(12.dp))
                    Divider(Modifier.weight(0.5f))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { onGoogleSignInButtonClicked() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = GoogleButtonColor),
                        modifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                            .weight(0.5f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = "Google icon",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Google",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 12.sp,
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { onFacebookSignInButtonClicked() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = FacebookButtonColor),
                        modifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                            .weight(0.5f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_facebook),
                                contentDescription = "Facebook icon",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Facebook",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 12.sp,
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.do_not_have_account),
                        color = AppBlackColor,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.sign_up),
                        color = AppBlueColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onSignUpButtonClicked() }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    loginTest()
}