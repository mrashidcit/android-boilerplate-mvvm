package com.android.boilerplate.view.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentSettingsBinding
import com.android.boilerplate.databinding.FragmentUsersBinding

/**
 * @author Abdul Rahman
 */
class SettingsFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun getViewModel(): BaseViewModel? = null

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun onLanguageClicked() {
        findNavController().navigate(
            SettingsFragmentDirections.actionDestSettingsToLanguagesFragment()
        )
    }
}