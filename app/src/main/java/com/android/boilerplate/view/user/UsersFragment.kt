package com.android.boilerplate.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentUsersBinding
import com.android.boilerplate.model.data.local.database.entities.RandomUser
import com.android.boilerplate.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class UsersFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: RandomUsersAdapter
    private lateinit var binding: FragmentUsersBinding

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
                R.layout.fragment_users,
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
            swipeRefreshLayout.setOnRefreshListener(this@UsersFragment)
            viewModel.randomUsers.observe(viewLifecycleOwner) {
                it.let {
                    swipeRefreshLayout.isRefreshing = false
                    setupRandomUsersAdapters(it)
                }
            }
            viewModel.getUsers()
        }
    }

    override fun loaderVisibility(visibility: Boolean) {
        binding.swipeRefreshLayout.apply {
            if (isRefreshing) {
                isRefreshing = visibility
            } else {
                super.loaderVisibility(visibility)
            }
        }
    }

    override fun onRefresh() {
        viewModel.getLatestUsers()
    }

    private fun setupRandomUsersAdapters(randomUsers: List<RandomUser>) {
        if (!::adapter.isInitialized) {
            adapter = RandomUsersAdapter(requireContext()) {
                findNavController().navigate(
                    UsersFragmentDirections.actionDestMainToDestUserDetails(it)
                )
            }
        }
        binding.apply {
            rvUsers.adapter = adapter
            adapter.submitList(randomUsers)
        }
    }
}