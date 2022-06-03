package com.android.boilerplate.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @author Abdul Rahman
 */
open class BaseViewModel : ViewModel() {

    val error = MutableLiveData<String>()
    val loader = MutableLiveData<Boolean>()
    val actionOnError = MutableLiveData<HttpException>()

    private fun showError(e: String) {
        error.postValue(e)
    }

    fun showLoader(show: Boolean) {
        loader.postValue(show)
    }

    private fun takeActionOnError(exception: HttpException) {
        actionOnError.postValue(exception)
    }

    fun handleException(exception: Exception) {
        showLoader(false)
        when (exception) {
            is TimeoutException, is SocketTimeoutException -> {
                showError(exception.message ?: exception.toString())
            }
            is UnknownHostException -> {
                showError(exception.message ?: exception.toString())
            }
            is HttpException -> {
                if (exception.code() == 401) takeActionOnError(exception)
                showError(exception.message ?: exception.toString())
            }
            else -> {
                showError(exception.message ?: exception.toString())
            }
        }
    }
}