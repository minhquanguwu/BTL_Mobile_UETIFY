package com.example.btl_mobile_spotify.navigation

import android.app.Activity
import android.content.Context
import android.support.v4.media.MediaMetadataCompat
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btl_mobile_spotify.exoplayer.MusicServiceConnection
import com.example.btl_mobile_spotify.screens.homescreen.HomeScreen
import com.example.btl_mobile_spotify.screens.libraryscreen.LibsScreen
import com.example.btl_mobile_spotify.screens.LoginScreen
import com.example.btl_mobile_spotify.screens.PremiumScreen
import com.example.btl_mobile_spotify.screens.ProfileScreen
import com.example.btl_mobile_spotify.screens.searchscreen.SearchScreen
import com.example.btl_mobile_spotify.screens.SignUpScreen
import com.example.btl_mobile_spotify.screens.SplashScreen
import com.example.btl_mobile_spotify.screens.homescreen.HomeViewModel
import com.example.btl_mobile_spotify.screens.playerfullscreen.MusicPlayerScreen
import com.example.btl_mobile_spotify.screens.playlist.PlaylistScreen
import com.example.btl_mobile_spotify.screens.sign_in.StartScreen
import com.example.btl_mobile_spotify.screens.sign_in.additional_method.AdditionalSignInViewModel
import com.example.btl_mobile_spotify.screens.sign_in.additional_method.GoogleAuthUiClient
import com.example.btl_mobile_spotify.screens.sign_in.native_method.NativeLoginViewModel
import com.example.btl_mobile_spotify.screens.sign_up.SignUpViewModel
import com.example.btl_mobile_spotify.utils.MusicUseCase
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun NavigationContainer(
    router: Router,
    navController: NavHostController,
    paddingValues: PaddingValues,
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
) {

    val startDestination = remember { mutableStateOf(Screen.Splash.route) }
    LaunchedEffect(startDestination) {
        if (startDestination.value == Screen.Start.route) {
            router.goHome()
        }
    }
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    val nativeLoginViewModel = NativeLoginViewModel(navController)
    val musicConnection = MusicServiceConnection(context)
    NavHost(navController = navController, startDestination = startDestination.value) {
        composable(Screen.Home.route) {
            HomeScreen(paddingValues, router)
        }
        composable(Screen.Search.route) {
            SearchScreen(paddingValues)
        }
        composable(Screen.Libs.route) {
            LibsScreen(paddingValues, router)
        }
        composable((Screen.Playlist.route)){
            PlaylistScreen()
        }
        composable(Screen.Premium.route) {
            PremiumScreen(paddingValues)
        }
        composable(Screen.Splash.route) {
            SplashScreen(
                goBack = {
                    startDestination.value = Screen.Start.route
                }
            )
        }
        composable(Screen.PlayerFull.route) {
            MusicPlayerScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                userInfo = nativeLoginViewModel.getSignedInUser(),
                onSignOut = {
                    Firebase.auth.signOut()
                    LoginManager.getInstance().logOut()
                    navController.navigate(Screen.Start.route)
                    musicConnection.pause()
                }, navController
            )
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController, NativeLoginViewModel(navController))
        }


        composable(route = Screen.Start.route) {
            val viewModel = viewModel<AdditionalSignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if(googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(Screen.Home.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == Activity.RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if(state.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate(Screen.Home.route)
                    viewModel.resetState()
                }
            }

            StartScreen(
                state = state,
                signInClick = {
                    lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                navController = navController
            )
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController, SignUpViewModel(navController))
        }
    }

}