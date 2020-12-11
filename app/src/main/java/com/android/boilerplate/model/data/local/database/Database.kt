package com.android.boilerplate.model.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.boilerplate.model.data.local.database.daos.UserDao
import com.android.boilerplate.model.data.local.database.entities.User

/**
 * @author Abdul Rahman
 */
@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
}