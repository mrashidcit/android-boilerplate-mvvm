package com.android.boilerplate.model.repository.main

import android.content.Context
import androidx.lifecycle.LiveData
import com.android.boilerplate.model.data.local.database.daos.UserDao
import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.model.data.remote.RemoteApi
import com.android.boilerplate.model.data.remote.request.UsersRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
class MainRepositoryImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userDao: UserDao,
    private val remote: RemoteApi
) :
    MainRepository {

    private var users = userDao.getUsersLiveData()

    override fun getUsersLiveData(): LiveData<List<User>> = users

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