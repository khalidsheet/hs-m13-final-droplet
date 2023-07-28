package com.khalidmsheet.droplet2.presentation.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khalidmsheet.droplet2.R

@Composable
fun AboutScreen() {
    val viewModel = viewModel<AboutViewModel>()
    val state by viewModel.state.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start
    ) {
        Text(text = "About", fontSize = 42.sp)
        if (state?.built == null) {
            Text(text = stringResource(R.string.loading_data))

        } else {
            Text(text = state?.built.toString())
        }
    }
}