package com.khalidmsheet.droplet2.presentation.sign_in

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

@Entity
data class UserData(
    @PrimaryKey(autoGenerate = false)
    val userId: String,

    val username: String? = "",
    val photo: String? = "",
)