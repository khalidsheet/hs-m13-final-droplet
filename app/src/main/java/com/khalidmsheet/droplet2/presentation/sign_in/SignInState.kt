package com.khalidmsheet.droplet2.presentation.sign_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val data: UserData? = null
)
