package com.khalidmsheet.droplet2.presentation.add

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.khalidmsheet.droplet2.R
import com.khalidmsheet.droplet2.presentation.navigation.Screen
import com.khalidmsheet.droplet2.presentation.sign_in.UserData
import com.khalidmsheet.droplet2.presentation.timeline.ThoughtData
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavHostController) {
    var thoughtText by remember { mutableStateOf("") }
    var hasBeenSubmitted by remember { mutableStateOf(false) }

    val db = FirebaseFirestore.getInstance()
    val auth = Firebase.auth
    val context = LocalContext.current

    fun onThoughtActionClicked() {
        hasBeenSubmitted = false

        val thoughtsData = ThoughtData(
            content = thoughtText,
            timestamp = Timestamp.now(),
            uid = auth.currentUser?.uid,
            username = auth.currentUser?.displayName,
            userId = auth.currentUser?.uid,
            photo = auth.currentUser?.photoUrl.toString()
        )

        db.collection("thoughts").add(thoughtsData).addOnSuccessListener {
            Toast.makeText(context, context.getString(R.string.awesome_let_s_go_see_it), Toast.LENGTH_LONG).show()
            hasBeenSubmitted = true
            thoughtText = ""
        }

    }

    LaunchedEffect(key1 = hasBeenSubmitted) {
        if (hasBeenSubmitted) {
            delay(1000)
            navController.navigate(Screen.TIMELINE.route)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = stringResource(R.string.write_your_thought))
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = thoughtText, onValueChange = { newText ->
                thoughtText = newText.trimStart()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            placeholder = {
                Text(text = stringResource(R.string.thoughts_are_shown_to_everyone_at_the_moment))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onThoughtActionClicked() },
            modifier = Modifier.fillMaxWidth(),
            enabled = thoughtText.length > 10
        ) {
            Text(text = stringResource(R.string.share_now))
        }
    }
}