package com.android.boilerplate.model.repository.users

import com.android.boilerplate.model.data.local.database.Database
import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.model.data.remote.RemoteApi
import com.android.boilerplate.model.data.remote.request.UsersRequest
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
@ActivityScoped
class UsersRepositoryImp @Inject constructor(
    private val local: Database,
    private val remote: RemoteApi
) :
    UsersRepository {

    override suspend fun getUsers(request: UsersRequest?): List<User>? {
        var users = local.userDao().getAll()
        if (users.isEmpty()) {
            val response = remote.getUsers(request?.results)
            response.body()?.results?.let {
                local.userDao().insert(it)
                users = it
            }
        }
        return users
    }

    override suspend fun getLatestUsers(request: UsersRequest?): List<User>? {
        val response = remote.getUsers(request?.results)
        response.body()?.results?.let {
            // delete the previously cached users
            local.userDao().delete(local.userDao().getAll())
            // insert the latest users
            local.userDao().insert(it)
            return it
        }
        return null
    }
}