package com.android.boilerplate.view.feedback

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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
import com.android.boilerplate.databinding.FragmentFeedbackBinding
import com.android.boilerplate.viewmodel.feedback.FeedbackViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class FeedbackFragment : BaseFragment() {

    private lateinit var binding: FragmentFeedbackBinding

    private val viewModel: FeedbackViewModel by viewModels()

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
                R.layout.fragment_feedback,
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
            etFeedback.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, co: Int, a: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let {
                        if (it.isNotEmpty()) {
                            etFeedback.error = null
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            viewModel.improveDesign.observe(viewLifecycleOwner) {
                it?.let {
                    setImproveDesign(it)
                }
            }
            viewModel.improveExperience.observe(viewLifecycleOwner) {
                it?.let {
                    setImproveExperience(it)
                }
            }
            viewModel.improveFunctionality.observe(viewLifecycleOwner) {
                it?.let {
                    setImproveFunctionality(it)
                }
            }
            viewModel.improvePerformance.observe(viewLifecycleOwner) {
                it?.let {
                    setImprovePerformance(it)
                }
            }
        }
    }

    fun onImproveClicked(item: Int) {
        viewModel.apply {
            when (item) {
                1 -> {
                    improveDesign.value?.let {
                        setImproveDesign(!it)
                    }
                }
                2 -> {
                    improveExperience.value?.let {
                        setImproveExperience(!it)
                    }
                }
                3 -> {
                    improveFunctionality.value?.let {
                        setImproveFunctionality(!it)
                    }
                }
                else -> {
                    improvePerformance.value?.let {
                        setImprovePerformance(!it)
                    }
                }
            }
        }
    }

    fun onSubmitRatingClicked() {
        if (validate()) {
            var description = ""
            viewModel.apply {
                improveDesign.value?.let {
                    if (it) {
                        description += "${getString(R.string.improve_design)} \n\n"
                    }
                }
                improveExperience.value?.let {
                    if (it) {
                        description += "${getString(R.string.improve_experience)} \n\n"
                    }
                }
                improveFunctionality.value?.let {
                    if (it) {
                        description += "${getString(R.string.improve_functionality)} \n\n"
                    }
                }
                improvePerformance.value?.let {
                    if (it) {
                        description += "${getString(R.string.improve_performance)} \n\n"
                    }
                }
            }
            if (!TextUtils.isEmpty(binding.etFeedback.text?.trim())) {
                description += "\n\n ${getString(R.string.detailed_feedback)}"
                description += "\n ${binding.etFeedback.text.toString()}"
            }
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("xyz@gmail.com"))
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    "${getString(R.string.feedback)} - ${getString(R.string.app_name)}"
                )
                putExtra(Intent.EXTRA_TEXT, description)
            }
            try {
                startActivity(Intent.createChooser(intent, getString(R.string.submit_feedback)))
            } catch (e: ActivityNotFoundException) {
                showToast(getString(R.string.no_email_app_installed))
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (TextUtils.isEmpty(etFeedback.text)) {
                etFeedback.error = getString(R.string.invalid_feedback)
                return false
            }
            return true
        }
    }

    private fun setImproveDesign(value: Boolean) {
        binding.apply {
            itemImproveDesign.apply {
                if (value) {
                    etFeedback.error = null
                    ivItem.setImageResource(R.drawable.ic_checked)
                } else {
                    ivItem.setImageResource(R.drawable.ic_unchecked)
                }
            }
        }
    }

    private fun setImproveExperience(value: Boolean) {
        binding.apply {
            itemImproveExperience.apply {
                if (value) {
                    etFeedback.error = null
                    ivItem.setImageResource(R.drawable.ic_checked)
                } else {
                    ivItem.setImageResource(R.drawable.ic_unchecked)
                }
            }
        }
    }

    private fun setImproveFunctionality(value: Boolean) {
        binding.apply {
            itemImproveFunctionality.apply {
                if (value) {
                    etFeedback.error = null
                    ivItem.setImageResource(R.drawable.ic_checked)
                } else {
                    ivItem.setImageResource(R.drawable.ic_unchecked)
                }
            }
        }
    }

    private fun setImprovePerformance(value: Boolean) {
        binding.apply {
            itemImprovePerformance.apply {
                if (value) {
                    etFeedback.error = null
                    ivItem.setImageResource(R.drawable.ic_checked)
                } else {
                    ivItem.setImageResource(R.drawable.ic_unchecked)
                }
            }
        }
    }
}