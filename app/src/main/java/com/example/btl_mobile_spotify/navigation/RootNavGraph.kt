package com.example.btl_mobile_spotify.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btl_mobile_spotify.screens.sign_in.GoogleAuthUiClient
import com.example.btl_mobile_spotify.screens.sign_in.SignInState
import com.example.btl_mobile_spotify.screens.sign_in.SignInViewModel
import com.example.btl_mobile_spotify.screens.sign_in.StartScreen
import com.google.android.gms.auth.api.identity.Identity
import androidx.lifecycle.lifecycleScope
import com.example.btl_mobile_spotify.screens.ProfileScreen
import com.google.android.gms.common.api.Scope
import com.google.firebase.firestore.core.ActivityScope
import kotlinx.coroutines.launch

@Composable
fun RootNavigationGraph(navController: NavHostController, context: Context,
                        lifecycleScope: LifecycleCoroutineScope) {
     val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.START
    ) {
        authNavGraph(navController = navController)
        contentNavGraph(navController = navController)

        composable(route = Graph.START) {
//            StartScreen(navController, signInClick = {}, SignInState())
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if(googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate("profile")
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == RESULT_OK) {
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

                    navController.navigate("profile")
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
                }, navController = navController
            )
        }
        composable(route = "profile") {
            ProfileScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    lifecycleScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.popBackStack()
                    }
                }, navController
            )
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val START = "home_graph"
    const val CONTENT = "details_graph"
}