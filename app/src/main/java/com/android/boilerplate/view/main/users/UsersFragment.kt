package com.android.boilerplate.view.main.users

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentUsersBinding
import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.viewmodel.main.users.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Abdul Rahman
 */
@AndroidEntryPoint
class UsersFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: UsersAdapter
    private lateinit var binding: FragmentUsersBinding

    private val viewModel: UsersViewModel by viewModels()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

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
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener(this@UsersFragment)
            viewModel.users.observe(viewLifecycleOwner) {
                it.let {
                    swipeRefreshLayout.isRefreshing = false
                    setupUsersAdapters(it)
                }
            }
            viewModel.getUsers()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                findNavController().navigate(
                    UsersFragmentDirections.actionDestUsersToDestSettings()
                )
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    override fun onRefresh() {
        viewModel.getLatestUsers()
    }

    private fun setupUsersAdapters(users: List<User>) {
        if (!::adapter.isInitialized) {
            adapter = UsersAdapter(requireContext()) {
                findNavController().navigate(
                    UsersFragmentDirections.actionDestUsersToDestUserDetail()
                )
            }
        }
        binding.apply {
            rvUsers.adapter = adapter
            adapter.submitList(users)
        }
    }
}