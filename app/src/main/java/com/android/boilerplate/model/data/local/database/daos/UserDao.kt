package com.android.boilerplate.model.data.local.database.daos

import androidx.room.*
import com.android.boilerplate.model.data.local.database.entities.User

/**
 * @author Abdul Rahman
 */
@Dao
interface UserDao {

    @Query("select * from user")
    suspend fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>)

    @Delete
    suspend fun delete(vararg user: User)

    @Delete
    suspend fun delete(users: List<User>)
}