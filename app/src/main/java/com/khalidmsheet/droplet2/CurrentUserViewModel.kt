package com.khalidmsheet.droplet2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalidmsheet.droplet2.presentation.sign_in.UserData
import kotlinx.coroutines.launch

class CurrentUserViewModel : ViewModel() {

    fun upsertCurrentUser(userData: UserData) {
        viewModelScope.launch {
//            dao.persistCurrentUser(user = userData)
        }
    }

    fun deleteCurrentUser(userData: UserData) {
        viewModelScope.launch {
//            dao.deleteCurrentUser(user = userData)
        }
    }
}