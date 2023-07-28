package com.khalidmsheet.droplet2.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.khalidmsheet.droplet2.presentation.sign_in.UserData

@Dao
interface UserDao {
    @Upsert
    suspend fun persistCurrentUser(user: UserData)

    @Delete
    suspend fun deleteCurrentUser(user: UserData)
}