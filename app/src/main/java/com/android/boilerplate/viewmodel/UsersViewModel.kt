package com.android.boilerplate.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.model.data.remote.request.UsersRequest
import com.android.boilerplate.model.repository.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Abdul Rahman
 */
class UsersViewModel @ViewModelInject constructor(private val repository: UsersRepository) :
    BaseViewModel() {

    val users = MutableLiveData<List<User>>()

    fun getUsers(): List<User>? {
        if (users.value?.isNotEmpty() == true) {
            return users.value
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    showLoader(true)
                    val response = repository.getUsers(UsersRequest(10))
                    showLoader(false)
                    response?.let {
                        users.postValue(it)
                    }
                } catch (exception: Exception) {
                    handleException(exception)
                }
            }
        }
        return null
    }

    fun getLatestUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoader(true)
                val response = repository.getLatestUsers(UsersRequest(10))
                showLoader(false)
                response?.let {
                    users.postValue(it)
                }
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }
}