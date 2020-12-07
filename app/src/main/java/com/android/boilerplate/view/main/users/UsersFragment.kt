package com.android.boilerplate.view.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.boilerplate.R
import com.android.boilerplate.base.view.BaseFragment
import com.android.boilerplate.base.viewmodel.BaseViewModel
import com.android.boilerplate.databinding.FragmentUsersBinding
import com.android.boilerplate.model.data.local.database.Database
import com.android.boilerplate.model.data.local.database.entities.User
import com.android.boilerplate.model.data.remote.RemoteApi
import com.android.boilerplate.model.repository.users.UsersRepositoryImp
import com.android.boilerplate.viewmodel.UsersViewModel

/**
 * @author Abdul Rahman
 */
class UsersFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: UsersAdapter
    private lateinit var viewModel: UsersViewModel
    private lateinit var binding: FragmentUsersBinding

    override fun getViewModel(): BaseViewModel? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Database.getDatabase(requireContext())
        val remoteApi = RemoteApi.getRemoteApi()
        val factory =
            UsersViewModel.Companion.UsersViewModelFactory(UsersRepositoryImp(database, remoteApi))
        viewModel = ViewModelProvider(this, factory).get(UsersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            viewModel.users.observe(viewLifecycleOwner, {
                it?.let {
                    swipeRefreshLayout.isRefreshing = false
                    setupUsersAdapters(it)
                }
            })
            viewModel.getUsers()
        }
    }

    override fun onRefresh() {
        viewModel.getLatestUsers()
    }

    private fun setupUsersAdapters(users: List<User>) {
        if (!::adapter.isInitialized) {
            adapter = UsersAdapter(requireContext())
        }
        binding.apply {
            rvUsers.adapter = adapter
            adapter.updateItems(users)
        }
    }
}