package com.khalidmsheet.droplet2.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khalidmsheet.droplet2.R

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    isLoading: Boolean = false
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Image(
        painter = painterResource(id = R.drawable.auth_bg),
        contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .scale(1.3f)
    )
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(24.dp, 60.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.auth_subtitle),
                color = colorResource(id = R.color.auth_subtitle),
                fontSize = 16.sp
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                .fillMaxWidth()
                .background(colorResource(id = R.color.main_color))
                .height(200.dp)
                .padding(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.auth_subheading),
                    color = colorResource(id = R.color.highlight_text),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = onSignInClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    if(isLoading) {
                        Text(
                            text = stringResource(id = R.string.loading),
                            modifier = Modifier.padding(6.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo_google),
                            contentDescription = "",
                            Modifier.size(24.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.auth_signin_text),
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}
