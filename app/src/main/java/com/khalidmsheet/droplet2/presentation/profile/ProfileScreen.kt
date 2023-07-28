package com.khalidmsheet.droplet2.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.khalidmsheet.droplet2.R
import com.khalidmsheet.droplet2.presentation.sign_in.SignInViewModel

@Composable
fun ProfileScreen() {
    val auth = Firebase.auth
    val viewModel = viewModel<SignInViewModel>()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (auth.currentUser?.photoUrl != null) {
            AsyncImage(
                model = auth.currentUser?.photoUrl.toString(),
                contentDescription = auth.currentUser?.displayName.toString(),
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .width(64.dp)
                    .height(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        if (auth.currentUser?.displayName != null) {
            Text(text = auth.currentUser?.displayName.toString())
        }

    }
}