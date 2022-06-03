package com.android.boilerplate.base.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.android.boilerplate.base.viewmodel.BaseViewModel
import retrofit2.HttpException

/**
 * @author Abdul Rahman
 */
abstract class BaseFragment : Fragment(), BaseView {

    private var activity: BaseActivity? = null

    abstract fun getViewModel(): BaseViewModel?

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            activity = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        getViewModel()?.let { viewModel ->
            viewModel.loader.observe(viewLifecycleOwner) {
                it?.let {
                    loaderVisibility(it)
                }
            }
            viewModel.error.observe(viewLifecycleOwner) {
                it?.let {
                    showToast(it)
                }
            }
            viewModel.actionOnError.observe(viewLifecycleOwner) {
                it?.let {
                    takeActionOnError(it)
                }
            }
        }
    }

    override fun changeStatusBarColor(color: Int) {
        activity?.changeStatusBarColor(color)
    }

    override fun resetStatusBarColor() {
        activity?.resetStatusBarColor()
    }

    override fun hideSystemBars(hide: Boolean, window: Window?, view: View?) {
        activity?.hideSystemBars(hide, window, view)
    }

    override fun setSoftInputMode(mode: Int) {
        activity?.setSoftInputMode(mode)
    }

    override fun resetSoftInputMode() {
        activity?.resetSoftInputMode()
    }

    override fun showKeyboard(editText: EditText) {
        activity?.showKeyboard(editText)
    }

    override fun hideKeyboard() {
        activity?.hideKeyboard()
    }

    override fun sessionExpire() {
        activity?.sessionExpire()
    }

    override fun noConnectivity() {
        activity?.noConnectivity()
    }

    override fun loaderVisibility(visibility: Boolean) {
        activity?.loaderVisibility(visibility)
    }

    override fun showToast(message: String?) {
        activity?.showToast(message)
    }

    override fun showSnackBar(view: View, message: String) {
        activity?.showSnackBar(view, message)
    }

    override fun takeActionOnError(exception: HttpException) {
        activity?.takeActionOnError(exception)
    }

    fun callBackKeyHandling(function: () -> Unit) {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    function()
                }
            }
        activity?.onBackPressedDispatcher?.addCallback(this, callback)
    }
}