package com.android.boilerplate.base.view

import android.widget.EditText

/**
 * @author Abdul Rahman
 */
interface BaseView {
    fun setSoftInputMode(mode: Int)
    fun resetSoftInputMode()
    fun showKeyboard(editText: EditText)
    fun hideKeyboard()
    fun sessionExpire()
    fun noConnectivity()
    fun loaderVisibility(visibility: Boolean)
    fun showToast(message: String?)
}