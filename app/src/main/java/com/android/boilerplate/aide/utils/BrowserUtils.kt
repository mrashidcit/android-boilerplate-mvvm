package com.android.boilerplate.aide.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.android.boilerplate.R

/**
 * @author Abdul Rahman
 */
object BrowserUtils {

    fun openInAppBrowser(context: Context, url: String) {
        val tabsIntentBuilder = CustomTabsIntent.Builder()
        tabsIntentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.purple_FF6200EE_black_101010))
        val tabsIntent = tabsIntentBuilder.build()
        tabsIntent.launchUrl(context, Uri.parse(url))
    }
}