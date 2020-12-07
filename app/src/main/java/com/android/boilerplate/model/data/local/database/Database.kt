package com.android.boilerplate.model.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.boilerplate.model.data.local.database.daos.UserDao
import com.android.boilerplate.model.data.local.database.entities.User

/**
 * @author Abdul Rahman
 */
@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private lateinit var database: com.android.boilerplate.model.data.local.database.Database

        fun getDatabase(context: Context): com.android.boilerplate.model.data.local.database.Database {
            if (!this::database.isInitialized) {
                database = Room.databaseBuilder(
                    context,
                    com.android.boilerplate.model.data.local.database.Database::class.java,
                    "db-boilerplate"
                ).build()
            }
            return database
        }
    }
}