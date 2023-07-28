package com.khalidmsheet.droplet2.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object TIMELINE : Screen(
        route = "TIMELINE",
        title = "Home",
        icon = Icons.Default.Home
    )

    object ADD : Screen(
        route = "ADD",
        title = "Create",
        icon = Icons.Default.Add
    )

    object PROFILE : Screen(
        route = "PROFILE",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object ABOUT : Screen(
        route = "ABOUT",
        title = "About",
        icon = Icons.Default.Info
    )
}
