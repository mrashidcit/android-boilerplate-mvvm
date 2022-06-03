package com.android.boilerplate.aide.utils

import android.os.Build

/**
 * @author Abdul Rahman
 */
object Utils {

    fun isM() = Build.VERSION.SDK_INT == Build.VERSION_CODES.M

    fun isMPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    fun isN() = Build.VERSION.SDK_INT == Build.VERSION_CODES.N

    fun isNPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

    fun isQ() = Build.VERSION.SDK_INT == Build.VERSION_CODES.Q

    fun isQPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun isR() = Build.VERSION.SDK_INT == Build.VERSION_CODES.R

    fun isRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    fun isS() = Build.VERSION.SDK_INT == Build.VERSION_CODES.S

    fun isSPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}