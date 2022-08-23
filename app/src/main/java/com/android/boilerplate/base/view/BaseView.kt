package com.android.boilerplate.base.view

import android.view.View
import android.view.Window
import android.widget.EditText

/**
 * @author Abdul Rahman
 */
interface BaseView {
    fun changeStatusBarColor(color: Int)
    fun resetStatusBarColor()
    fun hideSystemBars(hide: Boolean, window: Window?, view: View?)
    fun setSoftInputMode(mode: Int)
    fun resetSoftInputMode()
    fun showKeyboard(editText: EditText)
    fun hideKeyboard()
    fun sessionExpire()
    fun noConnectivity()
    fun loaderVisibility(visibility: Boolean)
    fun showToast(message: String?)
    fun showSnackBar(view: View, message: String)
    fun takeActionOnError(exception: Exception)
}