package com.android.boilerplate.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Abdul Rahman
 */
open class BaseViewModel : ViewModel() {

    val loader = MutableLiveData<Boolean>()
    val actionOnError = MutableLiveData<Exception>()

    fun showLoader(show: Boolean) {
        loader.postValue(show)
    }

    private fun takeActionOnError(exception: Exception) {
        actionOnError.postValue(exception)
    }

    fun handleException(exception: Exception) {
        showLoader(false)
        takeActionOnError(exception = exception)
    }
}