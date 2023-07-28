package com.khalidmsheet.droplet2.presentation.about

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalidmsheet.droplet2.data.api.About
import com.khalidmsheet.droplet2.data.api.AboutApiData
import com.khalidmsheet.droplet2.data.api.RetrofitClient
import com.khalidmsheet.droplet2.presentation.timeline.ThoughtData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AboutViewModel : ViewModel() {
    private val _state = MutableLiveData<AboutApiData>()
    var state: LiveData<AboutApiData> = _state


    init {
        getAbout()
    }

    private fun getAbout() {
        val retrofit = RetrofitClient.getInstance()
        val aboutResponse: About = retrofit.create(About::class.java)

        viewModelScope.launch {
            try {
                val response = aboutResponse.getAbout()
                if (response.isSuccessful) {
                    _state.value = AboutApiData(built = response.body()?.built)
                }

            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }
}
