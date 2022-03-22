package com.android.boilerplate.view.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.aide.utils.BrowserUtils
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentMoreBinding
import com.android.boilerplate.viewmodel.more.MoreViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class MoreFragment : BaseFragment() {

    private lateinit var binding: FragmentMoreBinding

    private val viewModel: MoreViewModel by viewModels()

    override fun getViewModel(): BaseViewModel? = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_more,
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
            layoutToolbar.ivBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    fun onSettingsClicked() {
        findNavController().navigate(MoreFragmentDirections.actionDestMoreToDestSettings())
    }

    fun onFeedbackClicked() {
        findNavController().navigate(MoreFragmentDirections.actionDestMoreToDestFeedback())
    }

    fun onPolicyClicked() {
        context?.let {
            val url = "https://www.google.com"
            BrowserUtils.openInAppBrowser(it, url)
        }
    }

    fun onShareClicked() {
        context?.let {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.share_message, getString(R.string.app_name), it.packageName)
                )
            }
            startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.share)))
        }
    }

    fun onRateUsClicked() {
        context?.let {
            try {
                val uriString = "market://details?id=${it.packageName}"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uriString)))
            } catch (e: Exception) {
                val url = "http://play.google.com/store/apps/details?id=${it.packageName}"
                BrowserUtils.openInAppBrowser(it, url)
            }
        }
    }

    fun onMoreAppsClicked() {
        context?.let {
            try {
                val uriString = "market://search?q=pub:XYZ"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uriString)))
            } catch (e: Exception) {
                val url = "http://play.google.com/store/search?q=pub:XYZ"
                BrowserUtils.openInAppBrowser(it, url)
            }
        }
    }

    fun onVersionClicked() {

    }
}