package com.android.boilerplate.aide.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author Abdul Rahman
 */
object NetworkUtils {

    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}