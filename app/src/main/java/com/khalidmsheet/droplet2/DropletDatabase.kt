package com.khalidmsheet.droplet2

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khalidmsheet.droplet2.data.user.UserDao
import com.khalidmsheet.droplet2.presentation.sign_in.UserData

@Database(
    entities = [UserData::class],
    version = 1,
    exportSchema = true
)
abstract class DropletDatabase : RoomDatabase() {

    abstract val dao: UserDao

}