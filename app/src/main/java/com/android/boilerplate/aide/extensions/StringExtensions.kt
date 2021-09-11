package com.android.boilerplate.aide.extensions

import java.util.*

/**
 * @author Abdul Rahman
 */

fun String.capitalizeFirstChar(): String {
    return this.substring(0, 1).toUpperCase(Locale.ROOT).plus(this.substring(1))
}