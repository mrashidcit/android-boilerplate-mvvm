package com.android.boilerplate.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.lang.Exception
import java.lang.RuntimeException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import kotlin.coroutines.CoroutineContext

/**
 * @author Abdul Rahman
 */
open class BaseViewModel : ViewModel() {

    val loader = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun loaderVisibility(visibility: Boolean) {
        loader.postValue(visibility)
    }

    fun showError(e: String) {
        error.postValue(e)
    }

    fun handleException(exception: Exception) {
        loaderVisibility(false)
        when (exception) {
            is TimeoutException -> {

            }
            is SocketTimeoutException -> {

            }
            is UnknownHostException -> {

            }
            is HttpException -> {

            }
            else -> {

            }
        }
    }
}