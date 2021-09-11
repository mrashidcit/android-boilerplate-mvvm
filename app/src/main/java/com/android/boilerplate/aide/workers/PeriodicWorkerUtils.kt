package com.android.boilerplate.aide.workers

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.android.boilerplate.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * @author Abdul Rahman
 */
object PeriodicWorkerUtils {

    fun createPeriodicWorker(context: Context) {
        val periodicWorkRequest = if (BuildConfig.DEBUG) {
            PeriodicWorkRequest
                .Builder(PeriodicWorker::class.java, 30, TimeUnit.SECONDS)
                .addTag(PeriodicWorker::class.java.simpleName)
                .build()
        } else {
            PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 7, TimeUnit.DAYS)
                .addTag(PeriodicWorker::class.java.simpleName)
                .build()
        }
        WorkManager.getInstance(context).enqueue(periodicWorkRequest)
    }

    fun cancelPeriodicWorker(context: Context) {
        WorkManager.getInstance(context).cancelAllWorkByTag(PeriodicWorker::class.java.simpleName)
    }
}