package com.android.boilerplate.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.remote.request.RandomUsersRequest
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

    val randomUsers = repository.getRandomUsersLiveData()

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoader(true)
                repository.getRandomUsers(RandomUsersRequest(10))
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
                repository.getLatestRandomUsers(RandomUsersRequest(10))
                showLoader(false)
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }
}