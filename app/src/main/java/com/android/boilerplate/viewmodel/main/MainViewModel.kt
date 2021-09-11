package com.android.boilerplate.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.remote.request.UsersRequest
import com.android.boilerplate.model.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) :
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