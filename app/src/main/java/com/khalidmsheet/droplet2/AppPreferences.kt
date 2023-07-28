package com.khalidmsheet.droplet2

import android.app.Activity
import android.content.Context

class AppPreferences(activity: Activity) {
    private val preferences = activity.getSharedPreferences("droplet", Context.MODE_PRIVATE)

    fun setUserImage(photo: String?) {
        with(preferences.edit()) {
            putString("userImage", photo)
            apply()
        }
    }

    fun getUserImage(): String? {
        return preferences.getString("userImage", null)
    }
}