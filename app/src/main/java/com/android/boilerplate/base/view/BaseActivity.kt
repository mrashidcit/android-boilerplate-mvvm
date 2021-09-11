package com.android.boilerplate.base.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.observe
import com.android.boilerplate.R
import com.android.boilerplate.aide.utils.DialogUtils
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.model.data.local.preference.Preferences
import java.util.*

/**
 * @author Abdul Rahman
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var dialog: Dialog
    private var availableNetwork: Network? = null
    private var originalSoftInputMode: Int? = null
    private lateinit var inputManager: InputMethodManager
    private lateinit var connectivityManager: ConnectivityManager

    abstract fun getViewModel(): BaseViewModel?

    abstract fun hasConnectivity(connectivity: Boolean)

    /**
     * @param newBase the default base context of the application
     * @return overridden configuration with user's selected night mode and language preferences
     */
    private fun getOverrideConfiguration(newBase: Context?): Configuration {
        val configuration = Configuration(newBase?.resources?.configuration)
        val appName = applicationInfo.loadLabel(packageManager).toString()
        val sharedPreferences = getSharedPreferences(appName, Context.MODE_PRIVATE)
        val uiMode = sharedPreferences.getInt(
            Preferences.KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
        AppCompatDelegate.setDefaultNightMode(uiMode)
        sharedPreferences.getString(Preferences.KEY_LANG, Preferences.KEY_DEFAULT)?.let {
            if (it != Preferences.KEY_DEFAULT) {
                configuration.setLocale(Locale(it))
            }
        }
        return configuration
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        applyOverrideConfiguration(getOverrideConfiguration(newBase))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogUtils.createProgressDialog(this, false)
        inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        getViewModel()?.let { viewModel ->
            viewModel.loader.observe(this) {
                it?.let {
                    loaderVisibility(it)
                }
            }
            viewModel.error.observe(this) {
                it?.let {
                    showToast(it)
                }
            }
        }
        registerNetworkCallback()
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        super.onDestroy()
    }

    override fun setSoftInputMode(mode: Int) {
        originalSoftInputMode = window.attributes.softInputMode
        window.setSoftInputMode(mode)
    }

    override fun resetSoftInputMode() {
        originalSoftInputMode?.let {
            window.setSoftInputMode(it)
        }
    }

    override fun showKeyboard(editText: EditText) {
        editText.post {
            editText.requestFocus()
            inputManager.showSoftInput(editText.rootView, InputMethodManager.SHOW_FORCED)
        }
    }

    override fun hideKeyboard() {
        this.currentFocus?.let {
            inputManager.hideSoftInputFromWindow(it.applicationWindowToken, 0)
        }
    }

    override fun sessionExpire() {
        showToast(getString(R.string.session_expired))
    }

    override fun noConnectivity() {
        showToast(getString(R.string.no_internet_connectivity))
    }

    override fun loaderVisibility(visibility: Boolean) {
        if (::dialog.isInitialized) {
            if (visibility) {
                if (!dialog.isShowing)
                    dialog.show()
            } else {
                dialog.dismiss()
            }
        }
    }

    override fun showToast(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            availableNetwork = network
            runOnUiThread { hasConnectivity(true) }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            if (network == availableNetwork) {
                runOnUiThread { hasConnectivity(false) }
            }
        }
    }

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}