package com.khalidmsheet.droplet2.presentation.timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khalidmsheet.droplet2.R

@Composable
fun TimelineScreen() {
    val viewModel = viewModel<ThoughtsViewModel>()
    val thoughts = viewModel.state.observeAsState(emptyList())


    if (thoughts.value.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(R.string.no_thoughts_yet))
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(1F)
            .padding(0.dp, 0.dp, 0.dp, 130.dp)
    ) {
        items(thoughts.value) { thought ->
            ThoughtCard(thought)
        }
    }
}