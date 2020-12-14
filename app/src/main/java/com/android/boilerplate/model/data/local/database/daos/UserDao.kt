package com.android.boilerplate.model.data.local.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.boilerplate.model.data.local.database.entities.User

/**
 * @author Abdul Rahman
 */
@Dao
interface UserDao {

    @Query("select * from user")
    fun getUsersLiveData(): LiveData<List<User>>

    @Query("select * from user")
    suspend fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>)

    @Delete
    suspend fun delete(users: List<User>)
}