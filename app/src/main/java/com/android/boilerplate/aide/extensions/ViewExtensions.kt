package com.android.boilerplate.aide.extensions

import android.view.View

/**
 * @author Abdul Rahman
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}