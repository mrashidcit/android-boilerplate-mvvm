package com.android.boilerplate.view.settings

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.aide.workers.PeriodicWorkerUtils
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentSettingsBinding
import com.android.boilerplate.viewmodel.settings.SettingsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel: SettingsViewModel by viewModels()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_settings,
                container,
                false
            )
            binding.listener = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            layoutToolbar.ivBack.setOnClickListener { findNavController().popBackStack() }
            setNotification(viewModel.getNotification())
            itemTheme.value = viewModel.getSelectedThemeName()
            itemLanguage.value = viewModel.getSelectedLanguageName()
        }
    }

    private fun setNotification(notification: Boolean, createWorker: Boolean = false) {
        binding.itemNotification.apply {
            context?.let { context ->
                if (notification) {
                    ivItem.setImageResource(R.drawable.ic_checked)
                    if (createWorker)
                        PeriodicWorkerUtils.createPeriodicWorker(context.applicationContext)
                } else {
                    ivItem.setImageResource(R.drawable.ic_unchecked)
                    if (createWorker)
                        PeriodicWorkerUtils.cancelPeriodicWorker(context.applicationContext)
                }
            }
        }
    }

    fun onNotificationClicked(){
        viewModel.setNotification(!viewModel.getNotification())
        setNotification(viewModel.getNotification(), true)
    }

    fun onThemeClicked() {
        context?.let {
            val dialogBuilder = MaterialAlertDialogBuilder(it, R.style.Alert)
            dialogBuilder.setTitle(getString(R.string.theme))
            dialogBuilder.setSingleChoiceItems(
                arrayOf(
                    getString(R.string.system_default),
                    getString(R.string.light),
                    getString(R.string.dark)
                ), viewModel.getSelectedThemeIndex()
            ) { dialog, which ->
                when (which) {
                    0 -> {
                        if (viewModel.getSelectedThemeIndex() != 0)
                            setTheme(dialog, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                    1 -> {
                        if (viewModel.getSelectedThemeIndex() != 1)
                            setTheme(dialog, AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    2 -> {
                        if (viewModel.getSelectedThemeIndex() != 2)
                            setTheme(dialog, AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
            }
            dialogBuilder.setNegativeButton(R.string.cancel)
            { dialog, _ -> dialog.dismiss() }
            val dialog = dialogBuilder.create()
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
        }
    }

    private fun setTheme(dialog: DialogInterface, theme: Int) {
        dialog.dismiss()
        viewModel.setTheme(theme)
        activity?.recreate()
    }

    fun onLanguageClicked() {
        findNavController().navigate(
            SettingsFragmentDirections.actionDestSettingsToLanguagesFragment()
        )
    }
}