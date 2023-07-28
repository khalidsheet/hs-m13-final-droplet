package com.khalidmsheet.droplet2.presentation.timeline

import com.google.firebase.Timestamp
import com.khalidmsheet.droplet2.presentation.sign_in.UserData

data class ThoughtData(
    var user: UserData? = null,
    val content: String = "",
    val timestamp: Timestamp? = null,
    var totalLikes: Int = 0,
    val uid: String? = "",
    val id: String? = "",
    val username: String? = "",
    val userId: String? = "",
    val photo: String? = ""
)
