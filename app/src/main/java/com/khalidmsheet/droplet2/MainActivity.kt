package com.khalidmsheet.droplet2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.MainThread
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.khalidmsheet.droplet2.data.api.About
import com.khalidmsheet.droplet2.data.api.RetrofitClient
import com.khalidmsheet.droplet2.presentation.main.MainScreen
import com.khalidmsheet.droplet2.ui.theme.Droplet2Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val activity = this
            val prefs = AppPreferences(activity)

            Droplet2Theme {
                Surface(
                    Modifier
                        .fillMaxSize()
                        .background(Color(0xFF222222)),
                ) {
                    MainScreen(prefs = prefs)
                }
            }
        }
    }
}

