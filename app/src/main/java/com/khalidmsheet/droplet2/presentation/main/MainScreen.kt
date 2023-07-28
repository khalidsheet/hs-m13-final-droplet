package com.khalidmsheet.droplet2.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.khalidmsheet.droplet2.AppPreferences
import com.khalidmsheet.droplet2.CurrentUserViewModel
import com.khalidmsheet.droplet2.DropletDatabase
import com.khalidmsheet.droplet2.R
import com.khalidmsheet.droplet2.presentation.navigation.BottomNavGraph
import com.khalidmsheet.droplet2.presentation.navigation.Screen
import org.jetbrains.annotations.Async

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(prefs: AppPreferences) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        topBar = {
            TopBar(prefs)
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun TopBar(prefs: AppPreferences) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(painter = painterResource(id = R.drawable.logo_dark), contentDescription = null)
                if(prefs.getUserImage() != null) {
                    AsyncImage(model = prefs.getUserImage().toString(), contentDescription = null, modifier = Modifier.clip(
                        RoundedCornerShape(50)
                    ))
                }
            }
        }
    }
}

@Composable
fun BottomBar(navigationController: NavHostController) {
    val screens = listOf(
        Screen.TIMELINE,
        Screen.ADD,
        Screen.PROFILE,
        Screen.ABOUT
    )
    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            screens.forEach { screen ->
                Button(
                    onClick = {
                        navigationController.navigate(screen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = colorResource(id = R.color.purple_500)
                    ),
                    enabled = currentDestination?.route != screen.route
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = screen.icon, contentDescription = null)
                            Text(text = screen.title)
                        }
                    }
                }
            }
        }
    }
}

