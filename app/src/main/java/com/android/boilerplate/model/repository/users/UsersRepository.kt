package com.android.boilerplate.model.repository.users

import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.model.data.remote.request.UsersRequest

/**
 * @author Abdul Rahman
 */
interface UsersRepository {

    suspend fun getUsers(request: UsersRequest?): List<User>?

    suspend fun getLatestUsers(request: UsersRequest?): List<User>?
}