package com.android.boilerplate.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.aide.extensions.setSafeOnClickListener
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentMainBinding
import com.android.boilerplate.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

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
                R.layout.fragment_main,
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
            layoutToolbar.ivMore.setOnClickListener {
                showOverflowMenu()
            }
            btnLoadUsers.setSafeOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionDestMainToDestUsers())
            }
        }
    }

    private fun showOverflowMenu() {
        PopupMenu(requireContext(), binding.layoutToolbar.ivMore).apply {
            menuInflater.inflate(R.menu.menu_main, menu)
            setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    item?.let {
                        when (item.itemId) {
                            R.id.menu_settings -> findNavController().navigate(
                                MainFragmentDirections.actionDestMainToDestSettings()
                            )
                            R.id.menu_feedback -> findNavController().navigate(
                                MainFragmentDirections.actionDestMainToDestFeedback()
                            )
                            R.id.menu_more -> findNavController().navigate(
                                MainFragmentDirections.actionDestMainToDestMore()
                            )
                        }
                        return true
                    }
                    return false
                }
            })
            show()
        }
    }
}