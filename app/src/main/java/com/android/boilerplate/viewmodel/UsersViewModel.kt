package com.android.boilerplate.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.remote.request.UsersRequest
import com.android.boilerplate.model.repository.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Abdul Rahman
 */
class UsersViewModel @ViewModelInject constructor(private val repository: UsersRepository) :
    BaseViewModel() {

    val users = repository.getUsersLiveData()

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoader(true)
                repository.getUsers(UsersRequest(10))
                showLoader(false)
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }

    fun getLatestUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoader(true)
                repository.getLatestUsers(UsersRequest(10))
                showLoader(false)
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }
}