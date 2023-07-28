package com.khalidmsheet.droplet2.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khalidmsheet.droplet2.AppPreferences
import com.khalidmsheet.droplet2.presentation.about.AboutScreen
import com.khalidmsheet.droplet2.presentation.add.AddScreen
import com.khalidmsheet.droplet2.presentation.profile.ProfileScreen
import com.khalidmsheet.droplet2.presentation.timeline.TimelineScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    Column {
        Spacer(modifier = Modifier.height(100.dp))
        Box(modifier = Modifier.padding(24.dp, 0.dp)) {
            NavHost(
                navController = navController,
                startDestination = Screen.TIMELINE.route
            ) {
                composable(route = Screen.TIMELINE.route) {
                    TimelineScreen()
                }

                composable(route = Screen.ADD.route) {
                    AddScreen(navController = navController)
                }

                composable(route = Screen.PROFILE.route) {
                    ProfileScreen()
                }

                composable(route = Screen.ABOUT.route) {
                    AboutScreen()
                }
            }
        }
    }
}