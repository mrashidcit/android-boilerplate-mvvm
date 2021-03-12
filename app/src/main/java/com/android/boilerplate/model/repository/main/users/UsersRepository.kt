package com.android.boilerplate.model.repository.main.users

import androidx.lifecycle.LiveData
import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.model.data.remote.request.UsersRequest

/**
 * @author Abdul Rahman
 */
interface UsersRepository {

    fun getUsersLiveData(): LiveData<List<User>>

    suspend fun getUsers(request: UsersRequest)

    suspend fun getLatestUsers(request: UsersRequest)
}