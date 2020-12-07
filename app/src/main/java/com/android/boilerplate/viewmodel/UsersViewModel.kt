package com.android.boilerplate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.model.data.remote.request.UsersRequest
import com.android.boilerplate.model.repository.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * @author Abdul Rahman
 */
class UsersViewModel(private val repository: UsersRepository) : BaseViewModel() {

    val users = MutableLiveData<List<User>>()

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loaderVisibility(true)
                val response = repository.getUsers(UsersRequest(10))
                loaderVisibility(false)
                response?.let {
                    users.postValue(it)
                }
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }

    fun getLatestUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loaderVisibility(true)
                val response = repository.getLatestUsers(UsersRequest(10))
                loaderVisibility(false)
                response?.let {
                    users.postValue(it)
                }
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }

    companion object {

        class UsersViewModelFactory(private val repository: UsersRepository) :
            ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(UsersRepository::class.java)
                    .newInstance(repository)
            }
        }
    }
}