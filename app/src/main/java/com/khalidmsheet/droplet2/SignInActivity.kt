package com.khalidmsheet.droplet2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.identity.Identity
import com.khalidmsheet.droplet2.presentation.sign_in.GoogleAuthUiClient
import com.khalidmsheet.droplet2.presentation.sign_in.SignInScreen
import com.khalidmsheet.droplet2.presentation.sign_in.SignInViewModel
import com.khalidmsheet.droplet2.ui.theme.Droplet2Theme
import kotlinx.coroutines.launch

class SignInActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val signInLoading = remember { mutableStateOf(false) }
            val activity = this
            val prefs = AppPreferences(activity)

            Droplet2Theme {
                Surface(
                    Modifier.fillMaxSize(),
                ) {
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsState()

                    LaunchedEffect(key1 = Unit) {
                        if (googleAuthUiClient.getSignedInUser() != null) {
                            prefs.setUserImage(googleAuthUiClient.getSignedInUser()?.photo)

                            val navigate = Intent(this@SignInActivity, MainActivity::class.java)
                            startActivity(navigate)
                        }
                    }

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val singInResult =
                                        googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                    viewModel.onSignInResult(singInResult)
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if (state.isSignInSuccessful) {
                            val userData = googleAuthUiClient.getSignedInUser()
                            if (userData != null) {
                                prefs.setUserImage(googleAuthUiClient.getSignedInUser()?.photo)
                            }
                            val navigate = Intent(this@SignInActivity, MainActivity::class.java)
                            startActivity(navigate)
//                            viewModel.resetState()
                            Log.d("State", "State reset")
                            signInLoading.value = false;
                        }
                    }

                    SignInScreen(
                        state = state,
                        isLoading = signInLoading.value,
                        onSignInClick = {
                            lifecycleScope.launch {
                                signInLoading.value = true
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

