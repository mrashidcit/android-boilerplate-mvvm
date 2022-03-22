package com.android.boilerplate.view.settings.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentLanguagesBinding
import com.android.boilerplate.model.data.aide.Language
import com.android.boilerplate.viewmodel.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class LanguagesFragment : BaseFragment() {

    private lateinit var adapter: LanguagesAdapter
    private lateinit var binding: FragmentLanguagesBinding

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
                R.layout.fragment_languages,
                container,
                false
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            layoutToolbar.ivBack.setOnClickListener { findNavController().navigateUp() }
            viewModel.languages.observe(viewLifecycleOwner) {
                it?.let { setupLanguagesAdapters(it) }
            }
            viewModel.getLanguages()
        }
    }

    private fun setupLanguagesAdapters(languages: List<Language>) {
        if (!::adapter.isInitialized) {
            adapter = LanguagesAdapter(requireContext()) {
                if(!viewModel.isSameLanguageSelected(it)) {
                    viewModel.setLanguage(it)
                    activity?.recreate()
                }
            }
        }
        binding.apply {
            rvLanguages.adapter = adapter
            adapter.submitList(languages)
        }
    }
}