package com.android.boilerplate.model.repository.users

import androidx.lifecycle.LiveData
import com.android.boilerplate.model.data.local.database.daos.UserDao
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
    private val userDao: UserDao,
    private val remote: RemoteApi
) : UsersRepository {

    private var users = userDao.getUsersLiveData()

    override fun getUsersLiveData(): LiveData<List<User>> {
        return users
    }

    override suspend fun getUsers(request: UsersRequest) {
        val users = userDao.getUsers()
        if (users.isEmpty()) {
            val response = remote.getUsers(request.results)
            response.body()?.results?.let {
                userDao.insert(it)
            }
        }
    }

    override suspend fun getLatestUsers(request: UsersRequest) {
        val response = remote.getUsers(request.results)
        response.body()?.results?.let {
            // delete the cached users
            users.value?.let { cachedUsers ->
                userDao.delete(cachedUsers)
            }
            // insert the latest users
            userDao.insert(it)
        }
    }
}