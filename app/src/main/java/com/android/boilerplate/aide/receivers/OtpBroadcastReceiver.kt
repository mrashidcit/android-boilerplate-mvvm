package com.android.boilerplate.helper.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

/**
 * @author Abdul Rahman
 */
class OtpBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (SmsRetriever.SMS_RETRIEVED_ACTION == it.action) {
                it.extras?.let { extras ->
                    (extras[SmsRetriever.EXTRA_STATUS] as Status?)?.let { status ->
                        when (status.statusCode) {
                            CommonStatusCodes.SUCCESS -> {
                                val message = extras[SmsRetriever.EXTRA_SMS_MESSAGE] as String?
                                message?.let { code ->
                                    try {
                                        val parts = code.split(" ")
                                        val otp = parts[parts.size - 2]
                                        val localIntent = Intent("intent_otp")
                                        localIntent.putExtra("otp", otp)
                                        context?.let { appContext ->
                                            LocalBroadcastManager.getInstance(appContext)
                                                .sendBroadcast(localIntent)
                                        }
                                    } catch (exception: Exception) {
                                        Log.e(this::class.java.simpleName, exception.toString())
                                    }
                                }
                            }
                            CommonStatusCodes.TIMEOUT -> {
                                val localIntent = Intent("intent_otp")
                                localIntent.putExtra("timeout", true)
                                context?.let { appContext ->
                                    LocalBroadcastManager.getInstance(appContext)
                                        .sendBroadcast(localIntent)
                                }
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }
}